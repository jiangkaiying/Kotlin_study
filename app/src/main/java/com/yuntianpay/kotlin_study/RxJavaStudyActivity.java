package com.yuntianpay.kotlin_study;

import android.os.Bundle;
import android.os.SystemClock;
import android.telecom.Call;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RxJavaStudyActivity extends AppCompatActivity {

    private String TAG = "RxJavaStudyActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_study);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseRxjava();
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapRxjava();
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zipRxjava();
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatRxjava();
            }
        });
        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flatMapRxjava();
            }
        });
        findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatMapRxjava();
            }
        });
        findViewById(R.id.btn7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distinctRxjava();
            }
        });
        findViewById(R.id.btn8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterRxjava();
            }
        });
        findViewById(R.id.btn9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bufferRxjava();
            }
        });
        findViewById(R.id.btn10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerRxjava();
            }
        });
        findViewById(R.id.btn11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intervalRxjava();
            }
        });
        findViewById(R.id.btn12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOnNextRxjava();
            }
        });
        findViewById(R.id.btn13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipRxjava();
            }
        });
        findViewById(R.id.btn14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeRxjava();
            }
        });
        findViewById(R.id.btn15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleRxjava();
            }
        });
        findViewById(R.id.btn16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                debounceRxjava();
            }
        });
        findViewById(R.id.btn17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deferRxjava();
            }
        });
        findViewById(R.id.btn18).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastRxjava();
            }
        });
        findViewById(R.id.btn19).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mergeRxjava();
            }
        });
        findViewById(R.id.btn20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reduceRxjava();
            }
        });
        findViewById(R.id.btn21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanRxjava();
            }
        });
        findViewById(R.id.btn22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowRxjava();
            }
        });
    }

    /**
     * creat 创建发射器  subscribe 绑定接受器
     */
    private void baseRxjava() {
        Log.e(TAG, "baseRxjava");
        //creat 发射器（subscribe 发射内容） 单一
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.w(TAG, "Observable emit 1");
                e.onNext("1");
                Log.w(TAG, "Observable emit 2");
                e.onNext("2");
                Log.w(TAG, "Observable emit 3");
                e.onNext("3");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.w(TAG, "accept values " + s);
            }
        });
        //just 携带多子弹发射器
        Observable.just(1, 2, 4, 5).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer s) throws Exception {
                Log.w(TAG, "accept values " + s);
            }
        });
        List<String> list = new ArrayList<>();
        list.add("语文考试60");
        list.add("数学考试80");
        list.add("英语考试50");
        //fromIterable 携带多子弹发射器
        Observable.fromIterable(list).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.w(TAG, "accept values " + s);
            }
        });


    }

    /**
     * 发射器发射数据 map操作符把发射器发送的数据在处理一下 接收器接受数据
     */
    private void mapRxjava() {
        Log.e(TAG, "mapRxjava");
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.w(TAG, "Observable emit 1");
                e.onNext("1");
                Log.w(TAG, "Observable emit 2");
                e.onNext("2");
                Log.w(TAG, "Observable emit 3");
                e.onNext("3");
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return s + "岁";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.w(TAG, "onNext values " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.w(TAG, "onError values " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.w(TAG, "onComplete values");
            }
        });
    }

    /**
     * 专用于合并事件，该合并不是连接（连接操作符后面会说），而是两两配对，也就意味着，最终配对出的 Observable 发射事件数目只和少的那个相同。
     * 1号发射器发射3个数据 2号发射器发射2个数据  两两对应才被接收器接收  那么1号的最后发送的数据将没有被接受
     */
    private void zipRxjava() {
        Log.e(TAG, "zipRxjava");
        Observable.zip(new ObservableSource<Integer>() {
            @Override
            public void subscribe(Observer<? super Integer> observer) {
                Log.w(TAG, "11Observable emit 1");
                observer.onNext(1);
                Log.w(TAG, "11Observable emit 2");
                observer.onNext(2);
                Log.w(TAG, "11Observable emit 3");
                observer.onNext(3);
            }
        }, new ObservableSource<Double>() {
            @Override
            public void subscribe(Observer<? super Double> observer) {
                Log.w(TAG, "22Observable emit 1");
                observer.onNext(1.1);
                Log.w(TAG, "22Observable emit 2");
                observer.onNext(1.2);
            }
        }, new BiFunction<Integer, Double, String>() {
            @Override
            public String apply(Integer integer, Double aDouble) throws Exception {
                return integer + "/" + aDouble;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.w(TAG, "onNext values " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.w(TAG, "onError values " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.w(TAG, "onComplete values");
            }
        });
    }

    /**
     * 合并发射器  把两个发射器合成1个发射器发送数据发送完1号在发送2号发射器
     */
    private void concatRxjava() {
        Log.e(TAG, "concatRxjava");
        Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer s) {
                Log.w(TAG, "onNext values " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.w(TAG, "onError values " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.w(TAG, "onComplete values");
            }
        });
    }

    /**
     * 将单一发射器 变成一个多发射器（也可是一个）  无序的
     * 比如：登陆接口得到token  通过flatmap用户token获取用户信息 才能叫登陆成功【1转1的情况】
     */
    private void flatMapRxjava() {
        Log.e(TAG, "flatMapRxjava");
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.w(TAG, "Observable emit 小明同学");
                e.onNext("小明");
                Log.w(TAG, "Observable emit 小黄同学");
                e.onNext("小黄");
            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {

                List<String> list = new ArrayList<>();
                list.add(s + "语文考试60");
                list.add(s + "数学考试80");
                list.add(s + "英语考试50");

                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MICROSECONDS);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.w(TAG, "onNext values " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.w(TAG, "onError values " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.w(TAG, "onComplete values");
            }
        });
    }

    /**
     * 将单一发射器 变成一个多发射器（也可是一个）  有序的
     * 比如：登陆接口得到token  通过flatmap用户token获取用户信息 才能叫登陆成功【1转1的情况】
     */
    private void concatMapRxjava() {
        Log.e(TAG, "concatMapRxjava");
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.w(TAG, "Observable emit 小明同学");
                e.onNext("小明");
                Log.w(TAG, "Observable emit 小黄同学");
                e.onNext("小黄");
            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {

                List<String> list = new ArrayList<>();
                list.add(s + "语文考试60");
                list.add(s + "数学考试80");
                list.add(s + "英语考试50");
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.w(TAG, "onNext values " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.w(TAG, "onError values " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.w(TAG, "onComplete values");
            }
        });
    }

    /**
     * 去除重复的发射器的发送内容
     */
    private void distinctRxjava() {
        Log.e(TAG, "distinctRxjava");
        Observable.just(1, 2, 3, 1, 5, 7, 4, 5, 2).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.w(TAG, "accept values " + integer);
            }
        });
    }

    /**
     * 过滤的发射器的发送内容
     */
    private void filterRxjava() {
        Log.e(TAG, "filterRxjava");
        Observable.just(7, 10, 9, 15, 7, 4, 18, 16).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                if (integer > 9) {
                    return true;
                }
                return false;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.w(TAG, "accept values " + integer);
            }
        });
    }

    /**
     * buffer(count,skip）
     */
    private void bufferRxjava() {
        Log.e(TAG, "bufferRxjava");
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).buffer(3, 2).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Exception {
                Log.e(TAG, "buffer size : " + integers.size() + "\n");
                for (Integer i : integers) {
                    Log.w(TAG, "accept values " + i);
                }
            }
        });
    }

    /**
     * timer 相当于一个定时任务
     * 默认在新线程
     */
    private void timerRxjava() {
        Log.e(TAG, "timerRxjava");
        Log.e(TAG, "timer start : " + getCurrentTimeFull() + "\n");
        Observable.timer(2, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.e(TAG, "timer end : " + getCurrentTimeFull() + "\n");
            }
        });
    }

    /**
     * 接受三个参数，分别是第一次发送延迟，间隔时间，时间单位。
     */
    private Disposable subscribe;
    private int number;

    private void intervalRxjava() {
        Log.e(TAG, "intervalRxjava");
        number = 0;
        Log.e(TAG, "timer start : " + getCurrentTimeFull() + "\n");
        subscribe = Observable.interval(3, 2, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long integer) throws Exception {
                number++;
                Log.w(TAG, "accept values " + integer);
                Log.e(TAG, "interval time : " + getCurrentTimeFull() + "\n");

                if (number > 8) {
                    if (subscribe != null && !subscribe.isDisposed()) {
                        subscribe.dispose();
                    }
                }

            }
        });
    }

    /**
     * doOnNext
     * 让订阅者在接收到数据之前干点有意思的事情。假如我们在获取到数据之前想先保存一下它，无疑我们可以这样实现
     */
    private void doOnNextRxjava() {
        Log.e(TAG, "doOnNextRxjava");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.w(TAG, "Observable emit 1");
                e.onNext(1);
                Log.w(TAG, "Observable emit 2");
                e.onNext(2);
                Log.w(TAG, "Observable emit 3");
                e.onNext(2);
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.w(TAG, "接收前，提前保存数据：" + integer);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.w(TAG, "accept values " + integer);
            }
        });
    }

    /**
     * skip 跳过count
     */
    private void skipRxjava() {
        Log.e(TAG, "skipRxjava");
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).skip(3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.w(TAG, "accept values " + integer);
            }
        });
    }

    /**
     * take 最多接收3个
     */
    private void takeRxjava() {
        Log.e(TAG, "takeRxjava");
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).take(3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.w(TAG, "accept values " + integer);
            }
        });
    }

    /**
     * single 只会接收一个参数，而 SingleObserver 只会调用 onError() 或者 onSuccess()
     */
    private void singleRxjava() {
        Log.e(TAG, "singleRxjava");
        Single.just(1).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer integer) {
                Log.e(TAG, "SingleObserver onSuccess" + integer);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * debounce
     * 去除发送频率过快的项，看起来好像没啥用处，但你信我，后面绝对有地方很有用武之地。
     */
    private void debounceRxjava() {
        Log.e(TAG, "debounceRxjava");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                Log.w(TAG, "Observable Emitter 1");
                e.onNext(1);
                Thread.sleep(3000);
                Log.w(TAG, "Observable Emitter 2");
                e.onNext(2);
                Thread.sleep(1000);
                Log.w(TAG, "Observable Emitter 3");
                e.onNext(3);
                Thread.sleep(2000);
                Log.w(TAG, "Observable Emitter 4");
                e.onNext(4);

            }
        }).debounce(2, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.w(TAG, "accept values " + integer);
            }
        });
    }

    /**
     * defer
     * 简单地时候就是每次订阅都会创建一个新的 Observable，并且如果没有被订阅，就不会产生新的 Observable。
     */
    private void deferRxjava() {
        Log.e(TAG, "deferRxjava");
        Observable<Integer> defer = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(1, 2, 3);
            }
        });
        defer.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.w(TAG, "accept values " + integer);
            }
        });
    }

    /**
     * last 操作符仅取出可观察到的最后一个值，或者是满足某些条件的最后一项。
     */
    private void lastRxjava() {
        Log.e(TAG, "lastRxjava");
        Observable.just(1, 2, 3, 4, 5).last(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.w(TAG, "accept values " + integer);
            }
        });
    }

    /**
     * merge合并发射器  把两个发射器合成1个发射器发射  不用等到 发射器 A 发送完所有的事件再进行发射器 B 的发送。
     */
    private void mergeRxjava() {
        Log.e(TAG, "mergeRxjava");
        Observable.merge(Observable.just(1, 2, 3), Observable.just(4, 5)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer s) {
                Log.w(TAG, "onNext values " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.w(TAG, "onError values " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.w(TAG, "onComplete values");
            }
        });
    }

    /**
     * reduce 操作符每次用一个方法处理一个值，可以有一个 seed 作为初始值。
     * 例如下面：1，2进入运算=3  下次进入(上次运算的结果)3，3=6 下次进入（上次运算的结果）6,4...
     */
    private void reduceRxjava() {
        Log.e(TAG, "reduceRxjava");
        Observable.just(1, 2, 3).reduce(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                Log.w(TAG, "apply values1 " + integer);
                Log.w(TAG, "apply values2 " + integer2);
                return integer + integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.w(TAG, "accept values " + integer);
            }
        });
    }

    /**
     * 和reduce 一样
     * 区别是 每次都会运算都会onnext 而reduce自会走一次onnext（最后的结果）
     */
    private void scanRxjava() {
        Log.e(TAG, "scanRxjava");
        Observable.just(1, 2, 3).scan(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                Log.w(TAG, "apply values1 " + integer);
                Log.w(TAG, "apply values2 " + integer2);
                return integer + integer2;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.w(TAG, "accept values " + integer);
            }
        });
    }

    /**
     * 按照实际划分窗口，将数据发送给不同的
     * 看打印的数据
     */
    private void windowRxjava() {
//        Observable.interval(1,TimeUnit.SECONDS).take(15).window(3) 0 1 2 3 4 5 6 7 8
        Log.e(TAG, "windowRxjava");
        Observable.interval(1, TimeUnit.SECONDS).take(15).window(3).subscribe(new Consumer<Observable<Long>>() {
            @Override
            public void accept(Observable<Long> longObservable) throws Exception {
                Log.e(TAG, "longObservable------");
                longObservable.subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.w(TAG, "accept values " + aLong);
                    }
                });
            }
        });
    }


    /**
     * 获取当前时间，全值
     *
     * @return
     */
    public String getCurrentTimeFull() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
