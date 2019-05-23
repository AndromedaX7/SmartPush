// IPoliceCodeInterface.aidl
package com.zhhl.smartpush;

// Declare any non-default types here with import statements

interface IPoliceCodeInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void set( String code);
    String get();
}
