package com.example.felix.gps

import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Binder
import android.os.IBinder
import android.support.v4.app.JobIntentService
import android.util.Log

import android.Manifest
import android.location.Location
import android.location.LocationManager.*
import android.support.v4.content.ContextCompat
import java.net.ConnectException

class GPS_LoggingJob : Service() {


    val interval: Long = 10 * 1000
    val distance: Float = 10.0f

    var locationManager: LocationManager? = null
    var logManager=LogManager("testfile.txt",this)
    var locationInfos = LocationInfos(logManager)




    var TAG = "LoggingService"

    override fun onBind(intent: Intent?): IBinder {
        Log.d(TAG,"Binding started")
        return GPS_LogServiceImpl(locationInfos)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.i(TAG, "Logging service started.")



        val gps_callback = GPS_LocationListener(locationInfos)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager


        // 0= Permission granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == 0) {

            locationManager?.requestLocationUpdates(GPS_PROVIDER, interval, distance, gps_callback)

            if (locationManager?.isProviderEnabled(GPS_PROVIDER) == false)
                // maybe show warning
                Log.d(TAG, "gps is not enabled -> will not update")
        } else {
            Log.i(TAG, "Permissions not granted")
        }



        return super.onStartCommand(intent, flags, startId)

    }

    class GPS_LogServiceImpl(val locationInfos: LocationInfos) : IGPS_Service.Stub() {
        override fun getLatitude(): Double {
            Log.d("hallöle",locationInfos.lastLocation!!.latitude.toString())
            return locationInfos.lastLocation!!.latitude
        }

        override fun getLongitude(): Double {

            return locationInfos.lastLocation!!.longitude

        }

        override fun getDistance(): Double {
            return 5.0
        }

        override fun getAverageSpeed(): Double {
            return locationInfos.averageSpeed.getMean()
        }


    }


    class AverageSpeed {

        var weightSum: Double = 0.0
        var sum: Double = 0.0

        fun addValue(value: Float, weight: Double) {

            sum += value * weight
            weightSum += weight

        }

        fun getMean(): Double {
            return sum / weightSum
        }

    }

    class LocationInfos(val logManager: LogManager){
        var averageSpeed=AverageSpeed()
        var lastLocation:Location?=null

        // TODO useful weights dependand on the time
        fun updateLocation(location:Location?){
            lastLocation=location
            averageSpeed.addValue(location!!.speed,1.0)

            logManager.addPoint(location)




        }




    }


}