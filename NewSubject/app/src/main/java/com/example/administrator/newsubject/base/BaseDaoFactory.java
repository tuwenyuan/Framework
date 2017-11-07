package com.example.administrator.newsubject.base;

import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.newsubject.utils.FileUtil;

/**
 * Created by twy on 2017/6/5.
 */

public class BaseDaoFactory {
    private String sqliteDataBasePath;
    private SQLiteDatabase sqLiteDatabase;
    private BaseDaoFactory(){
        sqliteDataBasePath = FileUtil.getcacheDirectory().getAbsolutePath()+"/ugou.db";
        openDatabase();
    }
    private static BaseDaoFactory instance = new BaseDaoFactory();
    public static BaseDaoFactory getInstance(){
        return instance;
    }

    private void openDatabase(){
        sqLiteDatabase  = SQLiteDatabase.openOrCreateDatabase(sqliteDataBasePath,null);
    }

    public synchronized BaseDao getDataHelper(Class entityClass){
        BaseDao baseDao = null;
        try
        {
            baseDao = new BaseDao();
            baseDao.init(entityClass,sqLiteDatabase);
        }catch (Exception e){
            e.printStackTrace();
        }
        return baseDao;
    }
}
