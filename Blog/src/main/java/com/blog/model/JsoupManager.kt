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


}