package com.example.administrator.newsubject.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newsubject.R;
import com.example.administrator.newsubject.base.BaseActivity;
import com.example.administrator.newsubject.base.BaseFragment;
import com.example.administrator.newsubject.entity.PageDataBean;
import com.example.administrator.newsubject.view.TitleView;

/**
 * Created by twy on 2017/6/1.
 */

public class TestFragment extends BaseFragment {

    private String title;
    private Button mBtnLoad;
    private TextView mTvMsg;

    public TestFragment(String title){
        this.title = title;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBtnLoad = (Button) view.findViewById(R.id.btn_load);
        mTvMsg = (TextView) view.findViewById(R.id.tv_msg);
    }

    @Override
    protected void initData() {
        mTvMsg.setText(title);
    }

    @Override
    protected void tryAgin() {
        showLoading(true);
        requestData(mContext.mService.getPageData(1, 20, null, 32), new BaseActivity.OnBackListener<PageDataBean>() {
            @Override
            public void onNext(PageDataBean data) {
                hideLoading();
                Toast.makeText(mContext,data.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                showErrorView();
            }
        });
    }

    @Override
    protected void initListener() {
        super.initListener();
        mBtnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading(true);
                requestData(mContext.mService.getPageData(1, 20, null, 32), new BaseActivity.OnBackListener<PageDataBean>() {
                    @Override
                    public void onNext(PageDataBean data) {
                        hideLoading();
                        Toast.makeText(mContext,data.toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                        showErrorView();
                    }
                });
            }
        });
    }

    @Override
    protected void initHeader(TitleView titleView) {
        titleView.showTitleView(null, "left_text", null, title,null,"right_text",null,true);
    }
}
