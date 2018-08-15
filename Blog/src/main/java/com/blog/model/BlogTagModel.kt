package com.blog.model

/**
 * by y on 24/11/2017.
 */
class BlogTagModel(var title: String, var littleTitle: String, var detailUrl: String, var type: Int, var pos: Int = -1) {
    companion object {
        const val TITLE = 0
        const val ITEM = 1
    }
}
