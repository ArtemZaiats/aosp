package com.gl.dummyservice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.NoSuchElementException;

import vendor.testdevice.dummy.V1_0.IDummy;
import vendor.testdevice.dummy.V1_0.IDummyCallback.Stub;

public class DummyService extends Service {

    private static final String TAG = "DummyService";
    private static final int MESSAGE_ID = 10;
    private static final String CHANNEL_ID = "com.gl.dummyservice.CHANNEL_ID";

    private IDummy hal;
    private ServiceCallback callback = new ServiceCallback();

    final RemoteCallbackList<IDummyServiceCallback> mCallbacks = new RemoteCallbackList<>();

    class ServiceCallback extends vendor.testdevice.dummy.V1_0.IDummyCallback.Stub {

        @Override
        public void handleMsg(String message) {
            toastMessage("handleMsg: " + message);
        }
    }

    public DummyService() {
        Log.d(TAG, "DummyService()");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showNotification();
        Log.d(TAG, "onCreate()");
        try {
            hal = vendor.testdevice.dummy.V1_0.IDummy.getService("default", true);
        } catch (NoSuchElementException e) {
            Log.e(TAG, " can't get HAL: " + e);
        } catch (RemoteException e) {
            Log.e(TAG, " can't get HAL: " + e);
        }
        if (hal == null) {
            return;
        }

        try {
            hal.registerCallback(callback);
        } catch (RemoteException e) {
            Log.e(TAG, " can't set Callback: " + e);
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        if (hal != null) {
            try {
                hal.unregisterCallback(callback);
            } catch (RemoteException e) {
                Log.e(TAG, " can't set Callback: " + e);
            }
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()");

        return new IDummyService.Stub() {
            @Override
            public void getMsg(String message) throws RemoteException {
                Log.d(TAG, "getMsg(String message)");
                if (hal != null) {
                    hal.getMsg( (r, m)->{toastMessage("getMsg: " + m); });
                } else {
                    toastMessage("getMsg: null HAL.");
                }
            }
            @Override
            public void update() throws RemoteException {
                Log.d(TAG, "update(String message)");
                if (hal != null) {
                    final int r = hal.update();
                } else {
                    toastMessage("update: null HAL.");
                }
            }
            @Override
            public void registerCallback(IDummyServiceCallback cb) {
                if(cb != null) mCallbacks.register(cb);
            }
            @Override
            public void unregisterCallback(IDummyServiceCallback cb) {
                if(cb != null) mCallbacks.unregister(cb);
            }
        };
    }

    private void toastMessage(String message) {
        final int N = mCallbacks.beginBroadcast();
        for(int i = 0; i < N; i++) {
            try{
                mCallbacks.getBroadcastItem(i).valueChanged(message);
                showNotification();
            } catch (RemoteException e) {
                Log.e(TAG, "toastMessage: Error",e );
            }
        }
        mCallbacks.finishBroadcast();
    }

    private void showNotification() {
        NotificationManager mNM = getSystemService(NotificationManager.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            mNM.createNotificationChannel(channel);
        }

        CharSequence text = getText(R.string.service_started);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(getText(R.string.app_name))
                .setContentText(text);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(MESSAGE_ID, notification.build());
    }
}
