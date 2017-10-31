package com.common.net.service

import com.common.net.NetApi
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * by y on 25/10/2017.
 */
interface ZLService {

    @GET(NetApi.ZL_LIST_API + "{suffix}/posts")
    fun getList(@Path("suffix") suffix: String, @Query("limit") limit: Int, @Query("offset") offset: Int): Observable<String>

    @GET(NetApi.ZL_DETAIL_API + "{slug}")
    fun getDetail(@Path("slug") slug: Int): Observable<String>

}