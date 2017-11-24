package com.common.net.service

/**
 * by y on 24/11/2017.
 */
interface SuccessCallback<in T> {

    fun remove()

    fun add(info: T)
}