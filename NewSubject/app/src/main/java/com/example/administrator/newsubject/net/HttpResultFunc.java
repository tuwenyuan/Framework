package com.example.administrator.newsubject.net;

import com.example.administrator.newsubject.exception.CustomDataException;

import rx.functions.Func1;

/**
 * @author 涂文远
 * @version $Rev$
 * @time 2016/12/24 11:44
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class HttpResultFunc<T> implements Func1<HttpResult,T> {
    @Override
    public T call(HttpResult httpResult) {
        if(httpResult instanceof HttpResultForUgou){
            HttpResultForUgou ugou = (HttpResultForUgou) httpResult;
            if("200".equals(ugou.errcode)){
                return (T) ugou.data;
            }else {
                throw new CustomDataException(ugou.errcode+"");
            }
        }else {
            throw new CustomDataException("没有该类型");
        }
    }
}

/*public class HttpResultFunc<T> implements Func1<Object,T> {
    @Override
    public T call(Object httpResult) {
        return (T)httpResult;
    }
}*/

