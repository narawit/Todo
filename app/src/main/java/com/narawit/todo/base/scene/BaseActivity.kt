package com.narawit.todo.base.scene

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.narawit.todo.utils.Constants

open class BaseActivity : AppCompatActivity() {
    val TAG: String? = this::class.java.name

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            Wrapper.wrap(
                newBase,
                Constants.DEFAULT_LANGUAGE,
                Constants.DEFAULT_FONT_SCALE
            )
        )
    }
}