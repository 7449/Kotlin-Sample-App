package com.codekk.model

import android.databinding.ObservableArrayList

/**
 * by y on 2017/5/16
 */

class CodekkOpListModel {

    lateinit var projectArray: ObservableArrayList<ProjectArrayBean>

    class ProjectArrayBean {

        var projectName: String? = null
        var createTime: String? = null
        var updateTime: String? = null
        var expiredTimes: Int = 0
        var usedTimes: Int = 0
        var voteUp: Int = 0
        var isRecommend: Boolean = false
        var isHide: Boolean = false
        var projectUrl: String? = null
        var demoUrl: String? = null
        var committer: String? = null
        var source: String? = null
        var lang: String? = null
        var authorName: String? = null
        var authorUrl: String? = null
        var codeKKUrl: String? = null
        var _id: String? = null
        var desc: String? = null
        var officialUrl: Any? = null
        var tags: List<TagsBean>? = null

        class TagsBean {
            var createTime: String? = null
            var name: String? = null
            var userName: String? = null
            var type: String? = null
        }
    }
}
