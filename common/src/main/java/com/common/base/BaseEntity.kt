package com.common.base

/**
 * by y.
 *
 *
 * Description:
 */
const val ENTITY_ERROR = 0
const val ENTITY_SUCCESS = 1
const val ENTITY_LOADING = 2
const val ENTITY_REFRESH = 3
const val ENTITY_LOADMORE = 4
const val ENTITY_NOMORE = 5
const val ENTITY_REFRESH_ERROR = 6
const val ENTITY_EMPTY = 7

class BaseEntity<T>(var type: Int, var page: Int = 0, var data: T? = null)
