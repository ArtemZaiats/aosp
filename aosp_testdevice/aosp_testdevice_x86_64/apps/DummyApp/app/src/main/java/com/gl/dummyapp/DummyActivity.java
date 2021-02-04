package com.gl.dummyapp;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.gl.dummyservice.IDummyService;

import androidx.appcompat.app.AppCompatActivity;

public class DummyActivity extends AppCompatActivity {

    IDummyService mDummyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DummyActivity", "onCreate(Bundle)");
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
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("DummyActivity", "onServiceDisconnected(ComponentName)");
            mDummyService = null;
        }
    };


    public void onCLickOne(View view) {
        try {
            Log.d("DummyActivity", "onClickOne(View)");
            mDummyService.getMsg("test");
        }
        catch (RemoteException e) {
            Log.d("DummyActivity", "onClickOne(View), exception");
        }
    }

    public void onCLicTwo(View view) {
        try {
            Log.d("DummyActivity", "onClickTwo(View)");
            mDummyService.update();
        }
        catch (RemoteException e) {
            Log.d("DummyActivity", "onClickTwo(View), exception");
        }
    }
}
