package com.example.felix.gps

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.Parcel
import android.util.Log

class CalcService : Service() {

    private val TAG="calcService"

    override fun onBind(intent: Intent): IBinder {

        return CalculatorServiceImpl()
    }

    override  fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG,"test")
        return super.onStartCommand(intent, flags, startId)
    }


    private class CalculatorServiceImpl:ICalcInterface.Stub() {
        override fun basicTypes(a: Int, b: Int, lis: IAsyncListener?) {
            lis?.OnResponse(a+b)
            Thread.sleep(3000)
            lis?.OnResponse(a+b*2)

        }


        override fun onTransact(code: Int, data: Parcel?, reply: Parcel?, flags: Int): Boolean {
            Log.d("lalalal","lul")
            return super.onTransact(code, data, reply, flags)

        }


    }
}
