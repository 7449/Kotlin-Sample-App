package com.zhihu

import com.common.App


/**
 *   @author y
 */
object ZhiHuConstant {

    const val FRAGMENT_INDEX = "fragment_index"
    const val FRAGMENT_TYPE = "fragment_type"

    const val ZHIHU = "zhihu"
    const val MOVIE = "movie"
    const val MUSIC = "music"
    const val DEVELOP = "develop"
    const val BOOK = "book"
    const val INTERNET = "internet"

    fun getTabName(type: String): Array<String> {
        return when (type) {
            ZhiHuConstant.ZHIHU -> App.instance.applicationContext.resources.getStringArray(R.array.zhihu_zhihu)
            ZhiHuConstant.MOVIE -> App.instance.applicationContext.resources.getStringArray(R.array.zhihu_movie)
            ZhiHuConstant.MUSIC -> App.instance.applicationContext.resources.getStringArray(R.array.zhihu_music)
            ZhiHuConstant.DEVELOP -> App.instance.applicationContext.resources.getStringArray(R.array.zhihu_develop)
            ZhiHuConstant.BOOK -> App.instance.applicationContext.resources.getStringArray(R.array.zhihu_book)
            else -> App.instance.applicationContext.resources.getStringArray(R.array.zhihu_internet)
        }
    }


    fun getSuffix(position: Int, type: String): String {
        return when (type) {
            ZhiHuConstant.ZHIHU -> App.instance.applicationContext.resources.getStringArray(R.array.zhihu_zhihu_suffix)[position]
            ZhiHuConstant.MOVIE -> App.instance.applicationContext.resources.getStringArray(R.array.zhihu_movie_suffix)[position]
            ZhiHuConstant.MUSIC -> App.instance.applicationContext.resources.getStringArray(R.array.zhihu_music_suffix)[position]
            ZhiHuConstant.DEVELOP -> App.instance.applicationContext.resources.getStringArray(R.array.zhihu_develop_suffix)[position]
            ZhiHuConstant.BOOK -> App.instance.applicationContext.resources.getStringArray(R.array.zhihu_book_suffix)[position]
            ZhiHuConstant.INTERNET -> App.instance.applicationContext.resources.getStringArray(R.array.zhihu_internet_suffix)[position]
            else -> ""
        }
    }

}