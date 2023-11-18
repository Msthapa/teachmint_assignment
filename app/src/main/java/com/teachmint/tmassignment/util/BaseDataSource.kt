/*
 *  Copyright (c) 2020. Embibe.  All rights reserved
 *
 */

package com.teachmint.tmassignment.util

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection


/**
 * Abstract Base Data source class with error handling
 */

abstract class BaseDataSource() {


    val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Exception","exception thrown in one of the children $throwable")
    }

     suspend fun <T> handleApiCall(call: suspend () -> Response<T>): DataWrapper<T> {

        return withContext(handler) {
            try {
                return@withContext withTimeoutOrNull(AppConstants.JOB_TIMEOUT) {
                    val response = withContext(Dispatchers.IO) { call() }
                    if (response.isSuccessful) {
                        return@withTimeoutOrNull handleSuccessfulResponse(response)
                    }
                    else {
                        Log.e("Exception","UnSuccessful response ${response.body()} - ${response.code()}")
                        return@withTimeoutOrNull handleErrorResponse(response)
                    }

                }?:error(HttpURLConnection.HTTP_GATEWAY_TIMEOUT, "timed out")
            }catch (e: HttpException) {
                return@withContext error(HttpURLConnection.HTTP_GATEWAY_TIMEOUT, e.message ?: e.toString())
            } catch (e: OutOfMemoryError) {
                return@withContext error(HttpURLConnection.HTTP_GATEWAY_TIMEOUT, e.message ?: e.toString())
            } catch (e: SocketTimeoutException) {
                return@withContext error(HttpURLConnection.HTTP_GATEWAY_TIMEOUT, e.message ?: e.toString())
            }catch (e: UnknownHostException) {
                return@withContext error(HttpURLConnection.HTTP_GATEWAY_TIMEOUT, e.message ?: e.toString())
            }catch (e: IOException) {
                return@withContext error(HttpURLConnection.HTTP_GATEWAY_TIMEOUT, e.message ?: e.toString())
            }catch (e: Exception) {
                return@withContext error(HttpURLConnection.HTTP_GATEWAY_TIMEOUT, e.message ?: e.toString())
            }
        }

    }

    private fun <T> handleSuccessfulResponse(response : Response<T>) : DataWrapper<T>
    {
        val body = response.body()
        return DataWrapper.success(response.code(),  body)
    }

    private fun <T> handleErrorResponse(response : Response<T>): DataWrapper<T>
    {
        response.errorBody()?.let {
            it.close()
            var jsonErr: JSONObject? = null
            try{
                jsonErr = JSONObject(it.string())
            }
            catch (_: java.lang.Exception){ }

            when (response.code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                    // getString method tends to crash,if field not available, using optString
                    if (jsonErr != null && jsonErr.optString("message")
                            .contains("The credentials you have entered is invalid")
                    ) {
                        return error(response.code(), jsonErr.getString("message"))
                    } else {
                        return error(response.code(), "Access Denied")
                    }
                }
                HttpURLConnection.HTTP_INTERNAL_ERROR ->{
                    return error(response.code(), jsonErr?.optString("message")?:"Something went wrong!")
                }
                else -> {
                    return error(response.code(), response.message())
                }
            }
        }
        return error(response.code(), response.message())
    }


    private fun <T> error(statusCode: Int, message: String): DataWrapper<T> {
        return DataWrapper.error(statusCode, message)
    }

}

