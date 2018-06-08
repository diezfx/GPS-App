package com.example.felix.gps

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.ResultReceiver
import android.support.v4.app.ActivityCompat

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), ServiceConnection {

    var service: Intent? = null
    var gpsService_Proxy:IGPS_Service?=null

    override fun onServiceDisconnected(p0: ComponentName?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




    override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {

        Log.d("hallo", "welt")

        gpsService_Proxy = IGPS_Service.Stub.asInterface(p1)


    }

    override fun onStop() {
        super.onStop()
        if(service!=null)
            stopService(service)
    }


    fun onUpdateClick(v:View){

        val lat:TextView=findViewById(R.id.Lat)
        lat.text=gpsService_Proxy?.latitude.toString()

        val lng:TextView=findViewById(R.id.lng)
        lng.text=gpsService_Proxy?.longitude.toString()

        val speed:TextView=findViewById(R.id.speed)
        speed.text=gpsService_Proxy?.averageSpeed.toString()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val j= Intent(this,CalcService::class.java)


        val btn: Button =findViewById(R.id.button)




        btn.setOnClickListener({v->onUpdateClick(v)})
        //startService(j)


        // intentservice other thread -> nonblocking
        // service main thread ->blocking


        //var result=GPS_Service_Result(Handler())
        //GPS_Service.startActionPrint(this,"hallo","welt",result)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Log.d("permissions", "granted")
        }
        // permissions not granted so far
        else {
            Log.d("permissions", "not granted")
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }


        val i = Intent(this, GPS_LoggingJob::class.java)

        startService()



    }


    fun startService() {
        val i = Intent(this, GPS_LoggingJob::class.java)

        startService(i)
        bindService(i,this,Context.BIND_AUTO_CREATE)

        service = i


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (Manifest.permission.ACCESS_FINE_LOCATION in permissions) {
            startService()
        }

    }







}




