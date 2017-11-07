# Framework
android 项目基本框架搭建 整合了mvp+retrofit+rxjava

![framework](https://github.com/tuwenyuan/Framework/blob/master/111111.gif)

#### 这样就完成了一次网络请求数据（包含了加载中视图 空视图 错误视图的处理）

    public void loadNetData(){
          showLoading(true);
          requestData(mService.getPageData1(1, 20, null, 32), new OnBackListener<PageDataBean>() {
              @Override
              public void onNext(PageDataBean dataBean) {
                  hideLoading();
                  Toast.makeText(MainActivity.this,dataBean.toString(),Toast.LENGTH_SHORT).show();
              }
              @Override
              public void onError(Throwable e) {
                  showErrorView();
              }
          });
      } 
      
#### 对头部进行了封装

 
     @Override
        protected void initHeader(TitleView titleView) {
            titleView.showTitleView(null, "left_text", null, title,null,"right_text",null,true);
        }
        
        
#### 所有请求不需要我们手动写代码解绑请求 BaseActivity/BaseFragment已经做了处理 具体实现可以看源码

     @Override
        protected void onDestroy() {
            super.onDestroy();
            if( netRequestWork.list.size()>0){
                for (Subscriber s :netRequestWork.list) {
                    s.unsubscribe();
                }
            }
        }
    
    
#### 更多功能请查看源码 适合大部分android项目架构 如有任何建议或bug欢迎骚扰
