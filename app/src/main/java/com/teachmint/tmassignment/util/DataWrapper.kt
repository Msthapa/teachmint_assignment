/*
 *  Copyright (c) 2020. Embibe.  All rights reserved
 *
 */

package com.teachmint.tmassignment.util


class DataWrapper<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val statusCode: Int = 0
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): DataWrapper<T> {
            return DataWrapper(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> success(statusCode: Int, data: T?): DataWrapper<T> {
            return DataWrapper(
                Status.SUCCESS,
                data,
                null,
                statusCode
            )
        }

        fun <T> error(message: String, data: T? = null): DataWrapper<T> {
            return DataWrapper(
                Status.ERROR,
                data,
                message
            )
        }

        fun <T> error(statusCode: Int, message: String, data: T? = null): DataWrapper<T> {
            return DataWrapper(
                Status.ERROR,
                data,
                message,
                statusCode
            )
        }

        fun <T> loading(data: T? = null): DataWrapper<T> {
            return DataWrapper(
                Status.LOADING,
                data,
                null
            )
        }
    }

    override fun toString(): String {
        return "DataWrapper(status=$status, data=$data, message=$message, statusCode=$statusCode)"
    }

}
