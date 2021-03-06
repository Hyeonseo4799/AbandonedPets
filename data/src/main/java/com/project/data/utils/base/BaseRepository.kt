package com.project.data.utils.base

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

open class BaseRepository {
    suspend inline fun <T> safeApiCall(crossinline call: suspend () -> T): T? {
        return try {
            val myObject = withContext(Dispatchers.IO) { call.invoke() }
            myObject
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                e.printStackTrace()
                Log.e("BaseRemoteRepo", "Call error: ${e.localizedMessage}", e.cause)
                null
            }
        }

    }
}
