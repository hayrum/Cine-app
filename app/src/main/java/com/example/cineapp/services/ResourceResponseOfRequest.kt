package com.example.cineapp.services


/**
 * @author Hayrum Vega
 * @since 09/10/2022
 * @description This enum class, represents a group of constants
 * (unchangeable variables, like final variables) for know the status of the request to API.
 */

enum class Status {
    SUCCESS, ERROR, NOT_FOUND, LOADING, NOT_AUTHORIZED_ACCESS, CONNECTION_ERROR;
}

data class ResourceResponse<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
) {
    companion object {
        fun <T> loading(data: T?): ResourceResponse<T> {
            return ResourceResponse(Status.LOADING, data)
        }

        fun <T> success(data: T?): ResourceResponse<T> {
            return ResourceResponse(Status.SUCCESS, data)
        }

        fun <T> error(msg: String, data: T?): ResourceResponse<T> {
            return ResourceResponse(Status.ERROR, data, msg)
        }
    }
}

