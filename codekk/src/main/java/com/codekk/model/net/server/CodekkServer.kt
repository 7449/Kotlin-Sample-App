package com.codekk.model.net.server

import com.codekk.model.*
import com.codekk.model.net.CodekkUrl
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author y
 */
interface CodekkServer {
    @GET("${CodekkUrl.OP_LIST_URL}{page}")
    fun getOpList(@Path("page") page: Int, @Query("type") type: String): Observable<CodekkModel<CodekkOpListModel>>

    @GET("${CodekkUrl.OP_DETAIL_URL}{id}/readme")
    fun getOpDetail(@Path("id") id: String): Observable<CodekkModel<CodekkReadmeModel>>

    @GET(CodekkUrl.OP_SEARCH_URL)
    fun getOpSearch(@Query("text") text: String, @Query("page") page: Int): Observable<CodekkModel<CodekkOpSearchModel>>

    @GET("${CodekkUrl.OPA_LIST_URL}{page}")
    fun getOpaList(@Path("page") page: Int): Observable<CodekkModel<CodekkOpaListModel>>

    @GET("${CodekkUrl.OPA_DETAIL_URL}{id}")
    fun getOpaDetail(@Path("id") id: String): Observable<CodekkModel<CodekkReadmeModel>>

    @GET("${CodekkUrl.OPA_SEARCH_URL}{name}/page/{page}")
    fun getOpaSearch(@Path("name") name: String, @Path("page") page: Int): Observable<CodekkModel<CodekkOpaSearchModel>>

    @GET("${CodekkUrl.JOB_LIST_URL}{page}")
    fun getJobList(@Path("page") page: Int): Observable<CodekkModel<CodekkJobListModel>>

    @GET("${CodekkUrl.JOB_DETAIL_URL}{id}")
    fun getJobDetail(@Path("id") id: String): Observable<CodekkModel<CodekkReadmeModel>>

    @GET("${CodekkUrl.BLOG_LIST_URL}{page}")
    fun getBlogList(@Path("page") page: Int): Observable<CodekkModel<CodekkBlogListModel>>

    @GET("${CodekkUrl.BLOG_DETAIL_URL}{id}")
    fun getBlogDetail(@Path("id") id: String): Observable<CodekkModel<CodekkReadmeModel>>

    @GET("${CodekkUrl.RECOMMEND_LIST_URL}{page}")
    fun getRecommendList(@Path("page") page: Int): Observable<CodekkModel<CodekkRecommendListModel>>

    @GET("${CodekkUrl.RECOMMEND_SEARCH_URL}{user}/page/{page}")
    fun getRecommendSearch(@Path("user") name: String, @Path("page") page: Int): Observable<CodekkModel<CodekkRecommendSearchModel>>
}
