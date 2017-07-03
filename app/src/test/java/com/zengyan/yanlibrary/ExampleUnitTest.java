package com.zengyan.yanlibrary;


import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static android.content.ContentValues.TAG;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
     private static final String TAG = ExampleUnitTest.class.getCanonicalName();

    @Test
    public void rxjava(){

//        被观察者
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                System.out.println("####send" + ": 1");
                observableEmitter.onNext(2);
                System.out.println("####send" + ": 2");
                observableEmitter.onNext(3);
                System.out.println("####send" + ": 3");
                observableEmitter.onComplete();
                System.out.println("####send" + ": complete");

            }
        });

        Observer<Integer> observer = new Observer<Integer>() {

            Disposable disposable;//一次性，可以在观察者方来自主截断接受数据


            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                System.out.println("####" +"onSubscribe"+disposable);
                this.disposable = disposable;

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("####onNext" +integer);
                if (integer == 2) {
                    disposable.dispose();
                }

            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("####" + "onError: "+throwable);
            }

            @Override
            public void onComplete() {
                System.out.println("####" + "onComplete: ");
            }
        };

        observable.subscribe(observer);

    }

    @Test
    public void map(){
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onNext(2);
                observableEmitter.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return String.valueOf(integer) + "!!!";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("####" + "accept: "+s);
            }
        });

    }

    @Test
    public void flatmap(){
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onNext(2);
                observableEmitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull final Integer integer) throws Exception {
                List<String> data = new ArrayList<String>();
                for (int i = 0; i < 3; i++) {
                    data.add(String.valueOf(integer)+"!!!!! number:" + i);
                }
                return Observable.fromIterable(data).delay(10, TimeUnit.MICROSECONDS);

            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("####" + "accept: "+s);
            }
        });

    }
}