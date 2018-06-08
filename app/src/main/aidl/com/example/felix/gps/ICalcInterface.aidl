// ICalcInterface.aidl

package com.example.felix.gps;

import com.example.felix.gps.IASyncListener;
// Declare any non-default types here with import statements

interface ICalcInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     oneway void basicTypes(in int a,in int b, in IAsyncListener lis);
}
