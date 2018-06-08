// IGPS_Service.aidl
package com.example.felix.gps;

// Declare any non-default types here with import statements

interface IGPS_Service {

    double getLatitude();
    double getLongitude();
    double getDistance();
    double getAverageSpeed();
}
