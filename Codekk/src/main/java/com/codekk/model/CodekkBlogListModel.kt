package com.codekk.model

/**
 * by y on 2017/5/19
 */

class CodekkBlogListModel {
    lateinit var summaryArray: List<SummaryArrayBean>

    class SummaryArrayBean {
        lateinit var _id: String
        var status: Int = 0
        lateinit var path: String
        lateinit var title: String
        lateinit var summary: String
        lateinit var authorName: String
        lateinit var authorUrl: String
        lateinit var type: String
        lateinit var tags: String
        lateinit var createTime: String
        lateinit var updateTime: String
        lateinit var codeKKUrl: String
        lateinit var fullTitle: String
        lateinit var tagList: List<String>
    }
}
