package com.rentwiz.app.coreui.widgets

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.updatePadding
import com.rentwiz.app.coreui.R
import com.rentwiz.app.coreui.databinding.RentwizCustomEditTextBinding
import com.rentwiz.app.coreui.getEnum

class RentwizCustomEditText
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {
    private lateinit var binding: RentwizCustomEditTextBinding
    private var maxLength: Int = 0
    private var isViewEnabled: Boolean = true
    private var inputType: RentwizEditTextType = RentwizEditTextType.TEXT
    private var hint: String? = null
    private var drawableEndIcon: Drawable? = null
    private var drawableStartIcon: Drawable? = null

    enum class RentwizEditTextType {
        TEXT, NUMBER, DATE, TEXT_EMAIL_ADDRESS
    }

    enum class IconPosition {
        START, END
    }

    init {
        readAttributes(context, attrs)
    }

    private fun readAttributes(context: Context, attrs: AttributeSet?) {
        attrs.let {
            val typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.RentwizCustomEditText)
            maxLength = typedArray.getInt(R.styleable.RentwizCustomEditText_max_length, 0)
            isViewEnabled =
                typedArray.getBoolean(R.styleable.RentwizCustomEditText_view_enabled, true)
            hint = typedArray.getString(R.styleable.RentwizCustomEditText_hint)
            inputType = typedArray.getEnum(R.styleable.RentwizCustomEditText_input_type, RentwizEditTextType.TEXT)
            drawableStartIcon =
                typedArray.getDrawable(R.styleable.RentwizCustomEditText_icon_start)
            drawableEndIcon =
                typedArray.getDrawable(R.styleable.RentwizCustomEditText_icon_end)
            typedArray.recycle()
        }
        initView()
    }

    private fun initView() {
        binding = RentwizCustomEditTextBinding.inflate(LayoutInflater.from(context), this, true)
        binding.textInputLayout.hint = hint
        setIcon(binding.iconStart, drawableStartIcon, IconPosition.START)
        setIcon(binding.iconEnd, drawableEndIcon, IconPosition.END)
        setStateEnabled(isViewEnabled)
        setMaxLength(maxLength)
        updateInputType()
    }

    private fun setStateEnabled(isEnabled: Boolean) {
        isViewEnabled = isEnabled
        binding.editext.isEnabled = isViewEnabled
    }

    private fun setIcon(imageView: ImageView, drawable: Drawable?, iconPosition: IconPosition) {
        if (drawable == null) {
            imageView.visibility = GONE
        } else {
            imageView.visibility = VISIBLE
            imageView.setImageDrawable(drawable)
            when(iconPosition) {
                IconPosition.START -> {
                    binding.editext.updatePadding(left = 100)
                }
                IconPosition.END ->{
                    binding.editext.updatePadding(right = 100)
                }
            }
        }
    }

    private fun setMaxLength(length: Int) {
        maxLength = length
        if(maxLength>0) {
            addInputFilters(arrayOf(InputFilter.LengthFilter(length)))
        }
    }

    private fun addInputFilters(filters: Array<InputFilter>) {
        binding.editext.filters += filters
    }

    private fun updateInputType() {
        if(isViewEnabled) {
            binding.editext.inputType = when(inputType) {
                RentwizEditTextType.TEXT -> InputType.TYPE_CLASS_TEXT
                RentwizEditTextType.NUMBER -> InputType.TYPE_CLASS_NUMBER
                RentwizEditTextType.DATE -> InputType.TYPE_DATETIME_VARIATION_DATE
                RentwizEditTextType.TEXT_EMAIL_ADDRESS -> InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }
        }
    }

}