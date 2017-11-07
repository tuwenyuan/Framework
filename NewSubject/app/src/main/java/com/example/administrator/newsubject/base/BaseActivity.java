package com.example.administrator.newsubject.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.administrator.newsubject.R;
import com.example.administrator.newsubject.net.ApiManagerService;
import com.example.administrator.newsubject.net.HostType;
import com.example.administrator.newsubject.net.NetRequestWork;
import com.example.administrator.newsubject.net.RetrofitManager;
import com.example.administrator.newsubject.view.TitleView;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by twy on 2017/5/25.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public NetRequestWork netRequestWork = new NetRequestWork();
    public ApiManagerService mService = RetrofitManager.builder(HostType.UGOU).mApiService;
    private ViewStub mContent;
    public View mLoadingView;
    public View mErrorView;
    public View mEmptyView;
    public boolean isUnusualView= false;
    private Button mBtnTry;
    public TitleView mTitleView;
    public RelativeLayout mRlBottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetcontentView();
        setContentView(R.layout.view_base);
        mContent = (ViewStub) findViewById(R.id.ll_content);
        mContent.setLayoutResource(getLayoutId());
        mContent.inflate();
        initView();
        initHeader(mTitleView);
        initData();
        initListener();
    }

    protected void initView(){
        mLoadingView = findViewById(R.id.rl_loading);
        mErrorView = findViewById(R.id.rl_error);
        mEmptyView = findViewById(R.id.rl_empty);
        mBtnTry = (Button) findViewById(R.id.btn_error_try_agin);
        mTitleView = (TitleView) findViewById(R.id.title_view);
        mRlBottom = (RelativeLayout) findViewById(R.id.rl_bottom);
    }

    protected abstract void initHeader(TitleView titleView);

    protected abstract void initData();

    /**
     * 发生错误点击重试执行
     */
    protected abstract void tryAgin();

    protected void initListener(){
        mBtnTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryAgin();
            }
        });
    }

    private void doBeforeSetcontentView(){}

    public abstract int getLayoutId();

    public void requestData(Observable observable, final OnBackListener listener){
        netRequestWork.RequestData(observable, new NetRequestWork.OnRequestListener() {
            @Override
            public void onRecvDataBack(Object o) {
                listener.onNext(o);
                if(isUnusualView){
                    mEmptyView.setVisibility(View.GONE);
                    mErrorView.setVisibility(View.GONE);
                }
                isUnusualView = false;
            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e);
            }
        });
    }

    public void addBottomView(View bottomView){
        //mRlBottom.addView(bottomView);
        mRlBottom.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if( netRequestWork.list.size()>0){
            for (Subscriber s :netRequestWork.list) {
                s.unsubscribe();
            }
        }
    }

    /**
     * 显示加载中视图
     * @param isShowBg 背景是否透明
     */
    public void showLoading(boolean isShowBg){
        if(isShowBg){
            mLoadingView.setBackgroundColor(Color.parseColor("#ffffff"));
        }else{
            mLoadingView.setBackgroundColor(Color.parseColor("#00ffffff"));
        }
        mLoadingView.setVisibility(View.VISIBLE);
    }


    public void hideLoading(){
        mLoadingView.setVisibility(View.GONE);
    }

    public void showErrorView(){
        isUnusualView = true;
        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }

    public void showEmptyView(){
        isUnusualView = true;
        mLoadingView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    public interface OnBackListener<T>{
        void onNext(T data);
        void onError(Throwable e);
    }
}
