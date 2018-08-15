package com.blog.model

import android.databinding.ObservableArrayList
import org.jsoup.nodes.Document

/**
 * by y on 03/11/2017.
 */
object JsoupManager {

    fun getBlogList(document: Document): ObservableArrayList<BlogListModel> {
        val list = ObservableArrayList<BlogListModel>()
        val elements = document.select("div.post-preview")
        for (element in elements) {
            val title = element.select("h2.post-title").text()
            val detailUrl = element.select("a[href]").attr("abs:href")
            val littleTitle = element.select("h3.post-subtitle").text()
            list.add(BlogListModel(title, littleTitle, detailUrl))
        }
        return list
    }

    fun getDetail(document: Document): BlogDetailModel = BlogDetailModel(document.select("div.row").eq(1).html(), "", "")

    fun getTag(document: Document): ObservableArrayList<BlogTagModel> {
        var tempPos = 0
        val list = ObservableArrayList<BlogTagModel>()
        val elements = document.select("div.one-tag-list")
        for (element in elements) {
            val littleTitle = element.select("span.tag-text").text()
            list.add(BlogTagModel("", littleTitle, "", BlogTagModel.ITEM, -1))
            val select = element.select("div.post-preview")
            for (contentElement in select) {
                val detailUrl = contentElement.select("a[href]").attr("abs:href")
                val title = contentElement.select("h2.post-title").text()
                val contentLittleTitle = contentElement.select("h3.post-subtitle").text()
                list.add(BlogTagModel(title, contentLittleTitle, detailUrl, BlogTagModel.TITLE, tempPos))
                tempPos++
            }
        }
        return list
    }
}