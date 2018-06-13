package com.example.felix.gps

import android.content.Context
import android.location.Location
import android.util.Log
import io.ticofab.androidgpxparser.parser.GPXParser

import java.io.File

class LogManager(val filename: String, val context: Context) {



    val gpx = GpxEncoder(context)
    var counter=4






    fun addPoint(location: Location) {

        gpx.addPt(location)
        if(counter>=5){
            saveTrack(filename)
            counter=0
        }
        else {
            counter++
        }
    }


    fun saveTrack(filename: String) {
        Log.d("LogManager","Track was saved.")

        var dir=context.getExternalFilesDir(null)
        var testFile= File(dir,filename)
        var content=gpx.saveToGpx()

        testFile.writeBytes(content.toByteArray())

    }


}