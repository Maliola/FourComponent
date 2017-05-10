package com.mahui.fourcomponent.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Administrator on 2017/5/9.
 */

public class MyService extends Service{
     String data = "默认消息";
    private boolean serviceRunning = false;
    private static final String TAG="mahui";
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"----onBind----");
        return new MyBinder();
    }
    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
        public void setData(String data) {
            MyService.this.data = data;
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"----onCreate----");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"----onStartCommand----");
        data = intent.getStringExtra("data");
        serviceRunning = true;
        new Thread() {
            @Override
            public void run() {
                int n = 0;
                while (serviceRunning) {
                    n++;
                    String str = n + "";
                    Log.e(TAG,str);
                    if (dataCallback != null) {
                        dataCallback.dataChanged(str);
                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG,"----onUnbind----");
        return super.onUnbind(intent);
    }
    @Override
    public void onDestroy() {
        serviceRunning = false;
        Log.e(TAG,"----onDestroy----");
        super.onDestroy();
    }
    DataCallback dataCallback = null;

    public DataCallback getDataCallback() {
        return dataCallback;
    }
    public void setDataCallback(DataCallback dataCallback) {
        this.dataCallback = dataCallback;
    }
    // 通过回调机制，将Service内部的变化传递到外部
    public interface DataCallback {
        void dataChanged(String str);
    }
}
