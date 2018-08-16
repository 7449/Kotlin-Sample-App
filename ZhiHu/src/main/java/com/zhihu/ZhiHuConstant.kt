package com.zhihu

import com.common.utils.UIUtils

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
            ZhiHuConstant.ZHIHU -> UIUtils.getStringArray(R.array.zhihu_zhihu)
            ZhiHuConstant.MOVIE -> UIUtils.getStringArray(R.array.zhihu_movie)
            ZhiHuConstant.MUSIC -> UIUtils.getStringArray(R.array.zhihu_music)
            ZhiHuConstant.DEVELOP -> UIUtils.getStringArray(R.array.zhihu_develop)
            ZhiHuConstant.BOOK -> UIUtils.getStringArray(R.array.zhihu_book)
            else -> UIUtils.getStringArray(R.array.zhihu_internet)
        }
    }


    fun getSuffix(position: Int, type: String): String {
        return when (type) {
            ZhiHuConstant.ZHIHU -> UIUtils.getStringArray(R.array.zhihu_zhihu_suffix)[position]
            ZhiHuConstant.MOVIE -> UIUtils.getStringArray(R.array.zhihu_movie_suffix)[position]
            ZhiHuConstant.MUSIC -> UIUtils.getStringArray(R.array.zhihu_music_suffix)[position]
            ZhiHuConstant.DEVELOP -> UIUtils.getStringArray(R.array.zhihu_develop_suffix)[position]
            ZhiHuConstant.BOOK -> UIUtils.getStringArray(R.array.zhihu_book_suffix)[position]
            ZhiHuConstant.INTERNET -> UIUtils.getStringArray(R.array.zhihu_internet_suffix)[position]
            else -> ""
        }
    }

}