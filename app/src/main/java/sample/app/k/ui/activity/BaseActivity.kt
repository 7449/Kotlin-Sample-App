package sample.app.k.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * by y on 27/09/2017.
 */
abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initById()
        initCreate(savedInstanceState)
    }

    protected fun <T : View> getView(id: Int): T = findViewById(id) as T

    abstract fun initCreate(savedInstanceState: Bundle?)

    abstract fun initById()

    abstract fun getLayoutId(): Int
}