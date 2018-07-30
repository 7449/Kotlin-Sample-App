package com.common.base

/**
 * by y.
 *
 *
 * Description:
 */
class BaseEntity<T>(var type: Int, var page: Int, var data: T?) {

    companion object {
        const val ERROR = 0
        const val SUCCESS = 1
    }
}
