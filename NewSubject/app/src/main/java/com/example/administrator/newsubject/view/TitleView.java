package com.example.administrator.newsubject.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.newsubject.R;

/**
 * Created by twy on 2017/6/1.
 */

public class TitleView extends FrameLayout{
    private ImageView ivLeft;
    private TextView tvLeft;
    private TextView tvCenter;
    private ImageView ivRight;
    private TextView tvRight;
    private View line;

    public TitleView(Context context) {
        this(context,null);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.view_title,null);
        initView(view);
        addView(view);
    }

    private void initView(View view) {
        ivLeft = (ImageView) view.findViewById(R.id.iv_title_left_icon);
        tvLeft = (TextView) view.findViewById(R.id.tv_title_left_msg);
        tvCenter = (TextView) view.findViewById(R.id.tv_title_center_msg);
        ivRight = (ImageView) view.findViewById(R.id.iv_title_right_icon);
        tvRight = (TextView) view.findViewById(R.id.tv_title_right_msg);
        line = view.findViewById(R.id.v_title_line);
        this.setVisibility(GONE);
    }

    /**
     * 显示title
     * @param leftIcon 左边图标 null 代表不显示图标
     * @param leftText 左边文案 null 代表不显示
     * @param leftLisener 左边点击监听事件
     * @param title 标题
     * @param rightIcon 右边图标
     * @param rightText 右边文案
     * @param rightListener 右边点击监听事件
     */
    public void showTitleView(Integer leftIcon,String leftText,OnClickListener leftLisener,String title,Integer rightIcon,String rightText,OnClickListener rightListener,boolean showDriverLine){
        this.setVisibility(VISIBLE);
        if(leftIcon!=null){
            ivLeft.setImageResource(leftIcon);
            if(leftLisener!=null){
                ivLeft.setOnClickListener(leftLisener);
            }
        }else{
            ivLeft.setVisibility(GONE);
        }
        if(leftText!=null){
            tvLeft.setText(leftText);
            if(leftLisener!=null){
                tvLeft.setOnClickListener(leftLisener);
            }
        }else{
            tvLeft.setVisibility(GONE);
        }
        if(title!=null){
            tvCenter.setText(title);
        }else{
            tvCenter.setVisibility(GONE);
        }
        if(rightIcon!=null){
            ivRight.setImageResource(rightIcon);
            if(rightListener!=null){
                ivRight.setOnClickListener(rightListener);
            }
        }else{
            ivRight.setVisibility(GONE);
        }
        if(leftText!=null){
            tvRight.setText(rightText);
            if(rightListener!=null){
                tvRight.setOnClickListener(rightListener);
            }
        }else{
            tvRight.setVisibility(GONE);
        }
        if(showDriverLine){
            line.setVisibility(VISIBLE);
        }else {
            line.setVisibility(GONE);
        }
    }
}
