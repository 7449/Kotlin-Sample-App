package com.common.net

import com.socks.library.KLog
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

/**
 * @author y
 */
class LogInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(chain.request())
        val body = response.body()
        if (body == null) {
            KLog.i("body == null")
            return response
        }
        val mediaType = body.contentType()
        val content = body.string()
        val logResponse = response.networkResponse()
        if (logResponse != null) {
            KLog.i(logResponse.request().headers())
        }
        KLog.i(" url : " + request.url() + "   " + content)
        return response.newBuilder().body(ResponseBody.create(mediaType, content)).build()
    }
}