package com.example.felix.gps

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
private const val ACTION_FOO = "com.example.felix.gps.action.FOO"
private const val ACTION_PRINT = "com.example.felix.gps.action.PRINT"

// TODO: Rename parameters
private const val EXTRA_PARAM1 = "com.example.felix.gps.extra.PARAM1"
private const val EXTRA_PARAM2 = "com.example.felix.gps.extra.PARAM2"
private const val EXTRA_PARAM3 = "com.example.felix.gps.extra.Result"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class GPS_Service : IntentService("GPS_Service") {

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_FOO -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionFoo(param1, param2)
            }
            ACTION_PRINT -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                val result =intent.getParcelableExtra<ResultReceiver>(EXTRA_PARAM3)
                handleActionBaz(param1, param2,result)
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo(param1: String, param2: String) {
        TODO("Handle action Foo")
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionBaz(param1: String, param2: String, result:ResultReceiver) {
        Log.d("test","ha,llo")

        Thread.sleep(5000)
        Log.d("test","part 2")

        var result_bundle=Bundle()
        result_bundle.putSerializable("text","hallo")

        result.send(1,result_bundle)

    }

    companion object {
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionFoo(context: Context, param1: String, param2: String) {
            val intent = Intent(context, GPS_Service::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }

        /**
         * Starts this service to perform action Baz with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionPrint(context: Context, param1: String, param2: String,result: ResultReceiver) {
            val intent = Intent(context, GPS_Service::class.java).apply {
                action = ACTION_PRINT
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
                putExtra(EXTRA_PARAM3,result)

            }
            context.startService(intent)
        }
    }
}
