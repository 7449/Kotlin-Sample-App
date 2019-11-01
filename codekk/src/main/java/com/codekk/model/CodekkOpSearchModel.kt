package com.codekk.model

/**
 * by y on 2017/5/17
 */

class CodekkOpSearchModel {

    var isIsFullSearch: Boolean = false
    var totalCount: Int = 0
    var pageSize: Int = 0
    var projectArray: List<ProjectArrayBean>? = null

    class ProjectArrayBean {
        var lang: String? = null
        var committer: String? = null
        var updateTime: String? = null
        var isHide: Boolean = false
        var authorUrl: String? = null
        var projectName: String? = null
        var expiredTimes: Int = 0
        var authorName: String? = null
        var usedTimes: Int = 0
        var source: String? = null
        var projectUrl: String? = null
        var officialUrl: Any? = null
        var isRecommend: Boolean = false
        var desc: String? = null
        var createTime: String? = null
        var voteUp: Int = 0
        var _id: String? = null
        var codeKKUrl: String? = null
        var tags: List<TagsBean>? = null

        class TagsBean {
            /**
             * userName : SmartDengg
             * contentId : 564b562cfbbec781c2f755dc
             * type : open-source-project
             * createTime : 2015-11-23T04:59:58.342000
             * name : RxJava
             */

            var userName: String? = null
            var contentId: String? = null
            var type: String? = null
            var createTime: String? = null
            var name: String? = null
        }
    }
}
