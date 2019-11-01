package com.codekk.model

/**
 * by y on 2017/5/18.
 */

class CodekkOpaSearchModel {

    var summaryArray: List<SummaryArrayBean>? = null

    class SummaryArrayBean {

        var _id: String? = null
        var status: Int = 0
        var path: String? = null
        var type: String? = null
        var summary: String? = null
        var phase: String? = null
        var tags: String? = null
        var createTime: String? = null
        var updateTime: String? = null
        var codeLang: String? = null
        var source: String? = null
        var title: String? = null
        var projectUrl: String? = null
        var projectName: String? = null
        var demoUrl: String? = null
        var authorUrl: String? = null
        var authorName: String? = null
        var codeKKUrl: String? = null
        var fullTitle: String? = null
        var tagList: List<String>? = null
    }
}
