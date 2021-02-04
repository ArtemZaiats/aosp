package com.gl.dummyservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import java.util.NoSuchElementException;

import vendor.testdevice.dummy.V1_0.IDummy;
import vendor.testdevice.dummy.V1_0.IDummyCallback.Stub;
import com.gl.dummyservice.IDummyService;

public class DummyService extends Service {

    private static final String TAG = "DummyService";

    private IDummy hal;
    private ServiceCallback callback = new ServiceCallback();

    class ServiceCallback extends vendor.testdevice.dummy.V1_0.IDummyCallback.Stub {

        @Override
        public void handleMsg(String message) {
            new Handler(Looper.getMainLooper()).post(() ->
                    toastMessage("handleMsg: " + message));
        }
    }

    public DummyService() {
        Log.d(TAG, "DummyService()");
    }

    @Override
    public void onCreate() {
        super.onCreate();
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
        };
    }

    private void toastMessage(String message) {
        Toast toast = Toast.makeText (getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
