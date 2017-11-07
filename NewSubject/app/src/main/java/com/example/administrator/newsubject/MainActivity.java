package com.example.administrator.newsubject;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.newsubject.adapter.MainPageAdapter;
import com.example.administrator.newsubject.base.BaseActivity;
import com.example.administrator.newsubject.entity.PageDataBean;
import com.example.administrator.newsubject.view.TitleView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button mBtn1;
    private Button mBtn2;
    private RadioGroup mRadioGroup;
    private ViewPager mVp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mBtn1 = (Button) findViewById(R.id.btn_loading_1);
        mBtn2 = (Button) findViewById(R.id.btn_loading_2);
        mVp = (ViewPager) findViewById(R.id.vp);
        View view = View.inflate(this,R.layout.view_bottom,mRlBottom);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.content_rg);
        addBottomView(view);
    }

    @Override
    protected void initHeader(TitleView titleView) {
        /*titleView.showTitleView(R.mipmap.ic_launcher, "左边", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"左边点击了",Toast.LENGTH_SHORT).show();
            }
        }, "标题", null, "右边", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"右边点击了",Toast.LENGTH_SHORT).show();
            }
        },true);*/
    }

    @Override
    protected void initData() {
        mVp.setAdapter(new MainPageAdapter(getSupportFragmentManager()));
        mVp.setCurrentItem(0);
    }

    @Override
    protected void tryAgin() {
        loadNetData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.content_rb_home://主页
                        mVp.setCurrentItem(0);
                        break;
                    case R.id.content_rb_news://购物车
                        mVp.setCurrentItem(1);
                        break;
                    case R.id.content_rb_hd://活动
                        mVp.setCurrentItem(2);
                        break;
                    case R.id.content_rb_cf://财富
                        mVp.setCurrentItem(3);
                        break;
                    case R.id.content_rb_wd://个人中心
                        mVp.setCurrentItem(4);
                        break;
                    default:
                        mVp.setCurrentItem(0);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_loading_1:
                loadNetData();
                break;
            case R.id.btn_loading_2:
                break;
        }
    }

    public void loadNetData(){
        showLoading(true);
        RequestData(mService.getPageData1(1, 20, null, 32), new OnBackListener<PageDataBean>() {
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
}
