package com.example.administrator.newsubject.net;
import com.example.administrator.newsubject.exception.CustomDataException;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by twy on 2017/5/27.
 */

public class NetRequestWork {
    public List<Subscriber> list = new ArrayList<>();
    private Subscriber createSubscriber(final OnRequestListener listener ){
        Subscriber mSubscriber = new Subscriber() {
            @Override
            public void onCompleted() {
                list.remove(this);
            }
            @Override
            public void onError(Throwable e) {
                list.remove(this);
                listener.onError(e);
            }
            @Override
            public void onNext(Object o) {
                listener.onRecvDataBack(o);
            }
            @Override
            public void onStart() {
                list.add(this);
            }
        };
        return mSubscriber;
    }

    public void RequestData(Observable observable, OnRequestListener listener){
        if(listener==null)
            throw new CustomDataException("OnRequestListener not null");
        observable.map(new HttpResultFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createSubscriber(listener));
    }

    public interface OnRequestListener{
        void onRecvDataBack(Object o);
        void onError(Throwable e);
    }
}
