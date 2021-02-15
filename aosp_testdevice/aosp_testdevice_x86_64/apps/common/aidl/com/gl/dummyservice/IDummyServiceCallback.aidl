package com.gl.dummyservice;

// Declare any non-default types here with import statements
interface IDummyServiceCallback {
    /**
     * Called when the service has a new value for you.
     */
    void valueChanged(String value);
}
