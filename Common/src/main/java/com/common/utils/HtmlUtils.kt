package com.common.utils

/**
 * by y on 27/09/2017.
 */
object HtmlUtils {

    fun getHtml(content: String): String {
        val css = "<link rel=\"stylesheet\" href=\"file:///android_asset/web.css\" type=\"text/css\">"
        return String.format("<!DOCTYPE html>\n"
                + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n"
                + "\t<meta charset=\"utf-8\" />\n</head>\n"
                + "<link rel=\"stylesheet\" href=\"file:///android_asset/web.css\" type=\"text/css\">"
                + "\n<body>"
                + css
                + "%s"
                + "</body>\n</html>", content)
    }

    val coding: String
        get() = "utf-8"

    val mimeType: String
        get() = "text/html"

}