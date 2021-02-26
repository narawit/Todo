package com.narawit.todo.base.scene

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.narawit.todo.R

abstract class BaseDialog<D, B : ViewBinding> : DialogFragment() {

    private var data: D? = null
    private var mIsCancelable = false
    private var bindView: ((B, D?) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.MyDialog)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun setData(data: D) {
        this.data = data
    }

    private fun setBindView(view: B, data: D?) {
        bindView?.let { it(view, data) }
    }

    fun setViewBind(bindView: (view: B, data: D?) -> Unit): BaseDialog<D, B> {
        this.bindView = bindView
        return this
    }

    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = createView(inflater, container)
        setBindView(view, data)
        return view.root
    }
}