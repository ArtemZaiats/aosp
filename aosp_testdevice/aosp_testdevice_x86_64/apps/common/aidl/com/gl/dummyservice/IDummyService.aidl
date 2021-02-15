package com.gl.dummyservice;

// Declare any non-default types here with import statements
import com.gl.dummyservice.IDummyServiceCallback;
interface IDummyService {
    void getMsg(String message);
    void update();

    void registerCallback(IDummyServiceCallback cb);
    void unregisterCallback(IDummyServiceCallback cb);
}
