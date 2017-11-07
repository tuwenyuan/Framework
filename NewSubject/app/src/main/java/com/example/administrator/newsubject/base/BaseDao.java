package com.example.administrator.newsubject.base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by twy on 2017/6/5.
 */

public class BaseDao<T> {
    private boolean isInit = false;
    private SQLiteDatabase sqLiteDatabase;
    private Class<T> entity;
    private String tableName;
    private Map<String, Field> cacheMap;

    public synchronized boolean init(Class<T> entity, SQLiteDatabase sqLiteDatabase) {
        if (!isInit) {
            this.sqLiteDatabase = sqLiteDatabase;
            this.entity = entity;
            cacheMap = new HashMap<>();
            if (entity.getAnnotation(DbTable.class) == null) {
                tableName = entity.getClass().getSimpleName();
            } else {
                tableName = entity.getAnnotation(DbTable.class).value();
            }
            if (!sqLiteDatabase.isOpen()) {
                return false;
            }
            createTable();
            isInit = true;
        }
        return false;
    }

    private void createTable() {
        StringBuilder strb = new StringBuilder();
        strb.append("create table if not exists " + tableName + "(");
        Field[] columnFields = entity.getDeclaredFields();
        String fieldName;
        String typeText;
        for (Field field : columnFields) {
            field.setAccessible(true);
            if (field.getAnnotation(DbFiled.class) != null) {
                fieldName = field.getAnnotation(DbFiled.class).value();
            } else {
                fieldName = field.getName();
            }
            Class type = field.getType();
            if (type == String.class) {
                typeText = " text,";
            } else if (type == Integer.class) {
                typeText = " int,";
            } else if (type == Long.class) {
                typeText = " long,";
            } else if (type == Double.class) {
                typeText = " double,";
            } else {
                continue;
            }
            strb.append(fieldName).append(typeText);
            cacheMap.put(fieldName, field);
        }
        strb.deleteCharAt(strb.length() - 1);
        strb.append(")");
        sqLiteDatabase.execSQL(strb.toString());
    }

    public Long insert(T entity) {
        ContentValues contentValues = getContentValues(entity);
        long insert = sqLiteDatabase.insert(this.tableName, null, contentValues);
        return insert;
    }

    public int update(T entity,T where){
        ContentValues contentValues = getContentValues(entity);
        Condition condition = new Condition(getContentValues(where));
        int update = sqLiteDatabase.update(this.tableName,contentValues, condition.getWhereClause(), condition.getWhereArgs());
        return update;
    }

    public int delete(T where){
        Condition condition = new Condition(getContentValues(where));
        int delete = sqLiteDatabase.delete(this.tableName, condition.getWhereClause(), condition.getWhereArgs());
        return delete;
    }

    public List<T> query(T where){
        return query(where,null,null,null);
    }
    public List<T> query(T where,String orderBy,Integer startIndex,Integer limit){
        String limitString = null;
        if(startIndex!=null && limit!=null){
            limitString = startIndex+","+limit;
        }
        Condition condition = new Condition(getContentValues(where));
        Cursor cursor;
        List<T> result = null;
        try{
            cursor = sqLiteDatabase.query(this.tableName,null,condition.getWhereClause(),condition.getWhereArgs(),null,null,orderBy,limitString);
            result = getResult(cursor,where);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private List<T> getResult(Cursor cursor, T where) {
        List list = new ArrayList<>();
        Object item;
        while (cursor.moveToNext()){
            try {
                item = where.getClass().newInstance();
                Iterator<Map.Entry<String,Field>> iterator = cacheMap.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<String,Field> entry = iterator.next();
                    Field field = entry.getValue();
                    String columnName = entry.getKey();
                    int columnIndex = cursor.getColumnIndex(columnName);
                    Class type = field.getType();
                    if(columnIndex!=-1){
                        if(type==String.class){
                            field.set(item,cursor.getString(columnIndex));
                        }else if(type==Double.class){
                            field.set(item,cursor.getDouble(columnIndex));
                        }else if(type==Integer.class){
                            field.set(item,cursor.getInt(columnIndex));
                        }else if(type== Long.class){
                            field.set(item,cursor.getLong(columnIndex));
                        }else if(type==byte[].class){
                            field.set(item,cursor.getBlob(columnIndex));
                        }else {
                            continue;
                        }
                    }
                }
                list.add(item);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private ContentValues getContentValues(T entity) {
        ContentValues contentValues = new ContentValues();
        try {
            for (Map.Entry<String, Field> me : cacheMap.entrySet()) {
                if (me.getValue().get(entity) == null) {
                    continue;
                }
                contentValues.put(me.getKey(),me.getValue().get(entity).toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return contentValues;
    }

    class Condition{
        private String whereClause;
        private String[] whereArgs;
        public Condition(ContentValues where){
            List list = new ArrayList<>();
            StringBuilder strb = new StringBuilder();
            strb.append(" 1=1 ");
            Set<String> keys = where.keySet();
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                String value = (String)where.get(key);
                strb.append(" and "+key+" =?");
                list.add(value);
            }
            this.whereClause = strb.toString();
            this.whereArgs = (String[])list.toArray(new String[list.size()]);
        }

        public String[] getWhereArgs(){
            return whereArgs;
        }

        public String getWhereClause(){
            return whereClause;
        }

    }


}
