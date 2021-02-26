package com.narawit.todo.scene.dialog

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.narawit.todo.R

class LoadingDialog(context: Context) : Dialog(context) {
    init {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setContentView(R.layout.dialog_loading)
    }
}
