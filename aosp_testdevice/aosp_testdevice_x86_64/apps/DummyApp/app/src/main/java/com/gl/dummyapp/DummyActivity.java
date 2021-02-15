package com.gl.dummyapp;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gl.dummyservice.IDummyService;
import com.gl.dummyservice.IDummyServiceCallback;

public class DummyActivity extends AppCompatActivity {

    IDummyService mDummyService;

    private static final String TAG = "DummyActivity";

    private IDummyServiceCallback mCallback = new IDummyServiceCallback.Stub() {
        public void valueChanged(String message) {
            Log.d(TAG, "valueChanged() called with: message = [" + message + "]");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(DummyActivity.this, "message: " + message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate(Bundle)");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        Intent intent = new Intent("com.gl.dummyservice.DummyService");
        intent.setPackage("com.gl.dummyservice");
        bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("DummyActivity", "onServiceConnected(ComponentName, Binder)");
            mDummyService = IDummyService.Stub.asInterface(service);
            try {
                mDummyService.registerCallback(mCallback);
            } catch (RemoteException e){
                Log.e(TAG, "onServiceConnected: ",e );
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected(ComponentName)");
            try {
                mDummyService.unregisterCallback(mCallback);
            } catch (RemoteException e){
                Log.e(TAG, "onServiceConnected: ",e );
            }
            mDummyService = null;
        }
    };


    public void onCLickOne(View view) {
        try {
            Log.d(TAG, "onClickOne(View)");
            mDummyService.getMsg("test");
        }
        catch (RemoteException e) {
            Log.d("DummyActivity", "onClickOne(View), exception");
        }
    }

    public void onCLicTwo(View view) {
        try {
            Log.d(TAG, "onClickTwo(View)");
            mDummyService.update();
        }
        catch (RemoteException e) {
            Log.d(TAG, "onClickTwo(View), exception");
        }
    }
}
