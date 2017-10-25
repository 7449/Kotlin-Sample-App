package sample.app.k.ui.widget.status

import android.support.annotation.StringDef

/**
 * by y on 25/10/2017.
 */
@Target()
@StringDef(StatusLayout.NORMAL, StatusLayout.LOADING, StatusLayout.EMPTY, StatusLayout.SUCCESS, StatusLayout.ERROR)
annotation class Status