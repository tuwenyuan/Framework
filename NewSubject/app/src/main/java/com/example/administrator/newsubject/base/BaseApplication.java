package com.example.administrator.newsubject.base;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Process;

import java.util.LinkedList;
import java.util.List;
/**
 * @author 涂文远
 * @version $Rev$
 * @time 2016/12/26 13:43
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class BaseApplication extends Application {
    /**
     * 应用实例
     **/
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mHandler = new Handler();
    }

    /**
     * 获得实例
     *
     * @return
     */
    public static BaseApplication getInstance() {
        return instance;
    }

    public Handler mHandler;
}
