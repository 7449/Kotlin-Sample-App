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
            ZhiHuConstant.ZHIHU -> UIUtils.getStringArray(R.array.zhihu)
            ZhiHuConstant.MOVIE -> UIUtils.getStringArray(R.array.movie)
            ZhiHuConstant.MUSIC -> UIUtils.getStringArray(R.array.music)
            ZhiHuConstant.DEVELOP -> UIUtils.getStringArray(R.array.develop)
            ZhiHuConstant.BOOK -> UIUtils.getStringArray(R.array.book)
            else -> UIUtils.getStringArray(R.array.internet)
        }
    }


    fun getSuffix(position: Int, type: String): String {
        return when (type) {
            ZhiHuConstant.ZHIHU -> UIUtils.getStringArray(R.array.zhihu_suffix)[position]
            ZhiHuConstant.MOVIE -> UIUtils.getStringArray(R.array.movie_suffix)[position]
            ZhiHuConstant.MUSIC -> UIUtils.getStringArray(R.array.music_suffix)[position]
            ZhiHuConstant.DEVELOP -> UIUtils.getStringArray(R.array.develop_suffix)[position]
            ZhiHuConstant.BOOK -> UIUtils.getStringArray(R.array.book_suffix)[position]
            ZhiHuConstant.INTERNET -> UIUtils.getStringArray(R.array.internet_suffix)[position]
            else -> ""
        }
    }

}