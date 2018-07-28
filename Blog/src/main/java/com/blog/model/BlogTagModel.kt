package com.blog.model

import com.common.base.mvvm.CommonModel
import com.xadapter.adapter.multi.MultiCallBack

/**
 * by y on 24/11/2017.
 */
class BlogTagModel(var title: String, var littleTitle: String, var detailUrl: String, private var type: Int, private var pos: Int = -1) : CommonModel(), MultiCallBack {
    override val itemType: Int
        get() = type
    override val position: Int
        get() = pos
}