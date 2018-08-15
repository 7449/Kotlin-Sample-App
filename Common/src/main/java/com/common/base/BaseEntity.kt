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
        const val LOADING = 2
        const val REFRESH = 3
        const val LOADMORE = 4
        const val REFRESH_ERROR = 5
        const val EMPTY = 6
    }
}
