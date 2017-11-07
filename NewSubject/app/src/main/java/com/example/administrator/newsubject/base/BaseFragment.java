package com.example.administrator.newsubject.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.administrator.newsubject.R;
import com.example.administrator.newsubject.net.NetRequestWork;
import com.example.administrator.newsubject.view.TitleView;
import rx.Observable;

/**
 * Created by twy on 2017/6/1.
 */

public abstract class BaseFragment extends Fragment {

    public BaseActivity mContext;
    private ViewStub mContent;
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private Button mBtnTry;
    public TitleView mTitleView;
    private RelativeLayout mRlBottom;
    public boolean isUnusualView= false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.view_base,null);
        mContent = (ViewStub) view.findViewById(R.id.ll_content);
        mContent.setLayoutResource(getLayoutId());
        mContent.inflate();
        initView(view);
        initHeader(mTitleView);
        initData();
        initListener();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected void initView(View view){
        mLoadingView = view.findViewById(R.id.rl_loading);
        mErrorView = view.findViewById(R.id.rl_error);
        mEmptyView = view.findViewById(R.id.rl_empty);
        mBtnTry = (Button) view.findViewById(R.id.btn_error_try_agin);
        mTitleView = (TitleView) view.findViewById(R.id.title_view);
        mRlBottom = (RelativeLayout) view.findViewById(R.id.rl_bottom);
    }

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

    protected abstract void initHeader(TitleView titleView);

    public void requestData(Observable observable, final BaseActivity.OnBackListener listener){
        mContext.netRequestWork.RequestData(observable, new NetRequestWork.OnRequestListener() {
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

    public void showEmptyView(){
        isUnusualView = true;
        mLoadingView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    public void showErrorView(){
        isUnusualView = true;
        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }

}
