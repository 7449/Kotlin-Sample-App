package com.codekk.model

/**
 * by y on 2017/5/18
 */

class CodekkOpaListModel {

    lateinit var summaryArray: List<SummaryArrayBean>

    class SummaryArrayBean {
        lateinit var _id: String
        var status: Int = 0
        lateinit var path: String
        lateinit var type: String
        lateinit var summary: String
        lateinit var phase: String
        lateinit var tags: String
        lateinit var createTime: String
        lateinit var updateTime: String
        lateinit var codeLang: String
        lateinit var source: String
        lateinit var title: String
        lateinit var projectUrl: String
        lateinit var projectName: String
        lateinit var demoUrl: String
        lateinit var authorUrl: String
        lateinit var authorName: String
        lateinit var codeKKUrl: String
        lateinit var fullTitle: String
        lateinit var tagList: List<String>
    }
}
