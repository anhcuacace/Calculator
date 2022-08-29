package com.example.calculator.widget

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.calculator.R

class MyTextView(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    private var textView: TextView? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_textview, this, true)
        textView = findViewById(R.id.textview)
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.MyTextView)

        val title = typedArray?.getString(R.styleable.MyTextView_textview_title) ?: ""
        if (context != null) {
            textView?.setBackgroundColor(context.getColor(R.color.background_textview))
        }
        val backgroundColorDefault = context?.getColor(R.color.background_textview)

        val colorText = typedArray?.getResourceId(R.styleable.MyTextView_textview_color, -1) ?: -1
        textView?.setTextColor(colorText)

        val src = backgroundColorDefault?.let {
            typedArray?.getResourceId(
                R.styleable.MyTextView_textview_src,
                it
            )
        }
            ?: -1
        textView?.setBackgroundResource(src)

        textView?.text = title
        typedArray?.recycle()
    }

    fun setOnClickTextView(callback: ((String) -> Unit)) {
        callback.invoke(textView?.text.toString())
        textView?.setBackgroundColor(context.getColor(R.color.purple_200))
        Handler(Looper.getMainLooper()).postDelayed(
            {
                textView?.setBackgroundColor(context.getColor(R.color.background_textview))
            }, 500
        )
    }
}