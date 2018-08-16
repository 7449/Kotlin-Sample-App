package com.codekk.model.net


/**
 * by y on 2017/5/16
 */

object CodekkUrl {
    private const val BASE_API = "http://api.codekk.com/"
    const val OP_LIST_URL = BASE_API + "op/page/" //获取开源项目
    const val OP_DETAIL_URL = BASE_API + "op/detail/" //获取单个开源项目 ReadMe
    const val OP_SEARCH_URL = BASE_API + "op/search" //搜索开源项目
    const val OPA_LIST_URL = BASE_API + "opa/page/" //获取源码解析文章列表
    const val OPA_DETAIL_URL = BASE_API + "opa/detail/" //获取单个源码解析文章详情
    const val OPA_SEARCH_URL = BASE_API + "opa/user/"
    const val JOB_LIST_URL = BASE_API + "job/page/" //获取职位内推文章列表
    const val JOB_DETAIL_URL = BASE_API + "job/detail/" //获取单个职位内推文章详情
    const val BLOG_LIST_URL = BASE_API + "blog/page/" //获取博客文章列表
    const val BLOG_DETAIL_URL = BASE_API + "blog/detail/" //获取单个博客文章详情
    const val RECOMMEND_LIST_URL = BASE_API + "recommend/page/" //获取今日推荐列表
    const val RECOMMEND_SEARCH_URL = BASE_API + "recommend/user/" //根据推荐者查询推荐列表
}
