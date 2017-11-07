package com.example.administrator.newsubject.factory;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.newsubject.base.BaseFragment;
import com.example.administrator.newsubject.fragment.TestFragment;
import com.example.administrator.newsubject.view.TitleView;

public class FragmentFactory {

    public static BaseFragment getFragmentNew(int index) {
        BaseFragment fragment;
        String str = "";
        switch (index) {
            case 0:
                str = "home";
                break;
            case 1:
                str = "shopping_cart";
                break;
            case 2:
                str = "event";
                break;
            case 3:
                str = "wealth";
                break;
            case 4:
                str = "mine";
                break;
            default:
                str = "home";
                break;
        }
        final String finalStr = str;
        fragment = new TestFragment(str);
        return fragment;
    }
}
