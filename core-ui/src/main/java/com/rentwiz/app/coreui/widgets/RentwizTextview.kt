package com.rentwiz.app.coreui.widgets

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textview.MaterialTextView
import com.rentwiz.app.coreui.R
import com.rentwiz.app.coreui.RentwizTextType
import com.rentwiz.app.coreui.getEnum

class RentwizTextview : MaterialTextView {
    private var textType: RentwizTextType = RentwizTextType.H1

    constructor(context: Context) : super(context) {
        initAttrs(context, attrs = null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(context, attrs = attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initAttrs(context, attrs = attrs)
    }

    constructor(context: Context, textType: RentwizTextType) : super(context) {
        this.textType = textType
        initAttrs(context, attrs = null)
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        attrs.let {
            val typeArray = context.obtainStyledAttributes(attrs, R.styleable.RentwizTextview)
            textType =
                typeArray.getEnum(R.styleable.RentwizTextview_rentwizTextType, RentwizTextType.H1)
            typeArray.recycle()
        }
        initTextStyle()
    }

    private fun initTextStyle() {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(textType.textFontSize))
        typeface = ResourcesCompat.getFont(context, textType.textTypeface)
    }
}