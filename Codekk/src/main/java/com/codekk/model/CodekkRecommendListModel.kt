package com.codekk.model

/**
 * by y on 2017/5/20.
 */

class CodekkRecommendListModel {

    var recommendArray: List<RecommendArrayBean>? = null

    class RecommendArrayBean {

        var isToCodeKKWx: Boolean = false
        var isIsDelete: Boolean = false
        var createTime: String? = null
        var url: String? = null
        var title: String? = null
        var desc: String? = null
        var type: String? = null
        var userName: String? = null
        var _id: String? = null
        var codeKKUrl: String? = null
        var isIsFavorite: Boolean = false
        var encodeUrl: String? = null
        var articleTags: List<ArticleTagsBean>? = null
        var contentTypes: List<String>? = null

        class ArticleTagsBean {
            /**
             * createTime : 2017-05-19T17:02:55.753Z
             * name : android
             * userName : 7449
             * type : article
             */

            var createTime: String? = null
            var name: String? = null
            var userName: String? = null
            var type: String? = null
        }
    }
}
