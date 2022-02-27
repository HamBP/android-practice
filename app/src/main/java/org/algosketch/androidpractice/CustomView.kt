package org.algosketch.androidpractice

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.lang.reflect.Method

class CustomView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private var showBackButton: Boolean = true
    private lateinit var title: String

    init {
        initAttrs(attrs)
        initView()
    }

    private fun initAttrs(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomView,
            0, 0
        ).apply {
            // 속성으로 전달받은 값을 대입하는 부분
            try {
                showBackButton = getBoolean(R.styleable.CustomView_showBackButton, true)
                title = getString(R.styleable.CustomView_title) ?: ""
            } finally {
                recycle()
            }
        }
    }

    private fun initView() {
        inflate(context, R.layout.view_custom, this)

        val titleTextView = findViewById<TextView>(R.id.title)
        val backButton = findViewById<ImageButton>(R.id.back_button)

        titleTextView.text = title
        if(!showBackButton) backButton.visibility = View.GONE
    }

    /**
     * View.setOnClickListener 에서 제공하는 것과 비슷한 역할
     */
    fun setOnBackListener(listener: OnBackListener) {
        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            listener.onClick(it)
        }
    }

    /**
     * Kotlin 에서 작성한 코드는 SAM 을 람다로 받을 수 없기 때문에
     * onClick 메서드를 람다로 받을 수 있도록 오버로딩 했다.
     */
    fun setOnBackListener(action: (view: View)->Unit) {
        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            action(it)
        }
    }

    interface OnBackListener {
        fun onClick(view: View)
    }
}