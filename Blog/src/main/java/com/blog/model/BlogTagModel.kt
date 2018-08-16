package com.blog.model

/**
 * by y on 24/11/2017.
 */
class BlogTagModel(var title: String, var littleTitle: String, var detailUrl: String, var type: Int) {
    companion object {
        const val ITEM = -1
        const val TITLE = 0
    }
}
