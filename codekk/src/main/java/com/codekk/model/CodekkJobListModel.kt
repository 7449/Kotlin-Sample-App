package com.codekk.model

/**
 * by y on 2017/5/18.
 */

class CodekkJobListModel {
    lateinit var summaryArray: List<SummaryArrayBean>

    class SummaryArrayBean {
        lateinit var _id: String
        var status: Int = 0
        lateinit var codeLang: String
        lateinit var source: String
        lateinit var title: String
        lateinit var summary: String
        lateinit var authorUrl: String
        lateinit var authorName: String
        lateinit var authorCity: String
        lateinit var type: String
        lateinit var createTime: String
        lateinit var expiredTime: String
        lateinit var codeKKUrl: String
        lateinit var fullTitle: String
    }
}
