package com.zhihu.model.net.server

import androidx.databinding.ObservableArrayList
import com.zhihu.model.ZhiHuDetailModel
import com.zhihu.model.ZhiHuListModel
import com.zhihu.model.net.ZhiHuUrl
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * by y on 25/10/2017.
 */
interface ZLServer {

    @GET(ZhiHuUrl.ZL_LIST_API + "{suffix}/posts")
    fun getList(@Path("suffix") suffix: String, @Query("limit") limit: Int, @Query("offset") offset: Int): Observable<ObservableArrayList<ZhiHuListModel>>

    @GET(ZhiHuUrl.ZL_DETAIL_API + "{slug}")
    fun getDetail(@Path("slug") slug: Int): Observable<ZhiHuDetailModel>

}