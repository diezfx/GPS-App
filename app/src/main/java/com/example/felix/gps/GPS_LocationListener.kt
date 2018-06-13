package com.example.felix.gps

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log

class GPS_LocationListener(val locationInfos: GPS_LoggingJob.LocationInfos):LocationListener {

    val TAG="LocationListener"
    override fun onLocationChanged(location: Location?) {
        Log.i(TAG,location?.longitude.toString())

        locationInfos.updateLocation(location)
        // TODO useful weights dependand on the time

    }



    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        Log.i(TAG,status.toString())
    }

    override fun onProviderEnabled(provider: String?) {
        Log.d(TAG,"gps was enabled")
    }

    override fun onProviderDisabled(provider: String?) {
        Log.d(TAG,"gps was disabled")
    }
}