package com.common.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * by y on 27/09/2017.
 */
abstract class BaseFragment : Fragment() {

    protected var mView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), container, false)
        }
        initById()
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initCreated(savedInstanceState)
    }


    protected fun <T : View> getView(id: Int): T = mView!!.findViewById<T>(id) as T

    abstract fun initCreated(savedInstanceState: Bundle?)


    abstract fun initById()


    abstract fun getLayoutId(): Int

}