package com.codekk.model

import android.databinding.ObservableArrayList

/**
 * by y on 2017/5/16
 */

class CodekkOpListModel {

    lateinit var projectArray: ObservableArrayList<ProjectArrayBean>

    class ProjectArrayBean {
        lateinit var projectName: String
        lateinit var createTime: String
        lateinit var updateTime: String
        var expiredTimes: Int = 0
        var usedTimes: Int = 0
        var voteUp: Int = 0
        var isRecommend: Boolean = false
        var isHide: Boolean = false
        lateinit var projectUrl: String
        lateinit var demoUrl: String
        lateinit var committer: String
        lateinit var source: String
        lateinit var lang: String
        lateinit var authorName: String
        lateinit var authorUrl: String
        lateinit var codeKKUrl: String
        lateinit var _id: String
        lateinit var desc: String
        lateinit var officialUrl: Any
        lateinit var tags: ObservableArrayList<TagsBean>

        class TagsBean {
            lateinit var createTime: String
            lateinit var name: String
            lateinit var userName: String
            lateinit var type: String
        }
    }
}
