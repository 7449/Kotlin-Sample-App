package com.codekk.model.net


import com.codekk.model.CodekkModel

import io.reactivex.functions.Function

/**
 * by y on 2017/5/16
 */

class NetFunc<T> : Function<CodekkModel<T>, T> {

    override fun apply(tBaseModel: CodekkModel<T>): T {
        if (tBaseModel.code != 0) {
            throw NullPointerException()
        }
        return tBaseModel.data!!
    }
}