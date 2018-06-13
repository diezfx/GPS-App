package com.example.felix.gps

import android.content.Context
import android.location.Location



data class TrackPt(val lat: Double, val lng: Double)
data class TrackSeg(val trackPtList: ArrayList<TrackPt> = ArrayList<TrackPt>())
data class Track(val trackSegList: ArrayList<TrackSeg> = ArrayList<TrackSeg>())


class GpxEncoder(val context: Context) {

    var track: Track = Track()


    public fun addPt(location: Location) {

        lateinit var trackSeg:TrackSeg
        // take last segment or create new
        if(track.trackSegList.size - 1>0){
            trackSeg = track.trackSegList[track.trackSegList.size - 1]
        }
        else{

           trackSeg= TrackSeg()
            track.trackSegList.add(trackSeg)
        }

        trackSeg.trackPtList.add(TrackPt(location.latitude, location.longitude))
    }

    public fun saveToGpx(): String {


        var content = ""
        content += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>"
        content += "<gpx version=\"1.1\" creator=\"Ersteller der Datei\">"
        content += "<name>Data</name>"
        content += "<trk><name>Example gpx</name><number>1</number>"

        for (trackSegment in track.trackSegList) {
            content += "<trkseg>"

            // trackpts
            for (trackPoint in trackSegment.trackPtList) {
                content += "<trkpt "
                content += "lat=" + "\"${trackPoint.lat}\""
                content +=" "
                content += "lon=\"${trackPoint.lng}\""
                content += "></trkpt>"
            }

            content += "</trkseg>"


        }
        content += "</trk></gpx>"

        return content
    }


}



