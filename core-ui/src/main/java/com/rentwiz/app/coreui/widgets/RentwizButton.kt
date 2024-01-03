package com.rentwiz.app.coreui.widgets

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.rentwiz.app.coreui.R
import com.rentwiz.app.coreui.getEnum
import com.rentwiz.app.coreui.utils.Utils

class RentwizButton: MaterialButton {

    private var buttonType: RentwizButtonType = RentwizButtonType.LARGE
    private var updateFirstEnabledState: Boolean? = null
    private var btnHeight: Int = R.dimen.dimen_50dp

    enum class RentwizButtonType {
        LARGE, DIALOG_POSITIVE, DIALOG_NEGATIVE, BANNER
    }

    constructor(context: Context) : super(context) {
       initAttrs(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttrs(context, attrs)
    }

    constructor(context: Context, buttonType: RentwizButtonType) : super(context) {
        this.buttonType = buttonType
        initAttrs(context, null)
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
       attrs.let {
           val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RentwizButton , 0, 0)
           buttonType = typedArray.getEnum(R.styleable.RentwizButton_rentwizButtonType, RentwizButtonType.LARGE)
           typedArray.recycle()
       }
        initButtonStyle()
    }

    private fun initButtonStyle() {
        initDefaults()
        initButtonType()
    }

    private fun initDefaults() {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
        letterSpacing = 0F
    }

    private fun initButtonType() {
        val btnCornerRadius: Int
        val colorSelector = ContextCompat.getColorStateList(context, R.color.button_color_selector)
        val colorWhite = ContextCompat.getColor(context, R.color.rentwiz_color_FFFFFF)

        when(buttonType) {
            RentwizButtonType.LARGE -> {
                backgroundTintList = colorSelector
                setTextColor(colorWhite)
                btnHeight = R.dimen.dimen_60dp
                btnCornerRadius = R.dimen.dimen_8dp
            }
            RentwizButtonType.DIALOG_POSITIVE -> {
                backgroundTintList = colorSelector
                setTextColor(colorWhite)
                btnHeight = R.dimen.dimen_50dp
                btnCornerRadius = R.dimen.dimen_4dp
            }
            RentwizButtonType.DIALOG_NEGATIVE -> {
                backgroundTintList = ColorStateList.valueOf(colorWhite)
                strokeWidth = resources.getDimensionPixelSize(R.dimen.dimen_2dp)
                strokeColor = colorSelector
                setTextColor(colorSelector)
                btnHeight = R.dimen.dimen_50dp
                btnCornerRadius = R.dimen.dimen_4dp
            }
            RentwizButtonType.BANNER -> {
                backgroundTintList = ColorStateList.valueOf(colorWhite)
                setTextColor(colorSelector)
                btnHeight = R.dimen.dimen_40dp
                btnCornerRadius = R.dimen.dimen_4dp
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)
            }
        }
        stateListAnimator = null
        shapeAppearanceModel = ShapeAppearanceModel()
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, resources.getDimension(btnCornerRadius))
            .build()
        updateFontAndElevation()
        updateFirstEnabledStateIfNeeded()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val params = layoutParams
        params.height = resources.getDimensionPixelSize(btnHeight)
        layoutParams = params
    }

    override fun setEnabled(enabled: Boolean) {
        /* null check is required because, setEnabled() will be invoked during super constructor call
        during which buttonType field wont be initialized */
        if (buttonType != null) {
            //For blocking disabled state for some button type
            if (isDisablingAllowed()) {
                super.setEnabled(enabled)
                //Since there are font change for enabled and disabled state, update font accordingly
                updateFontAndElevation()
            }
        } else {
            /*This is used to update enabled state after initializing buttonType.
             This case occurs only once during init if android:enabled is used in xml*/
            updateFirstEnabledState = enabled
        }
    }

    private fun isDisablingAllowed(): Boolean {
        return when (buttonType) {
            RentwizButtonType.LARGE -> true
            else -> false
        }
    }

    private fun updateFontAndElevation() {
        typeface = ResourcesCompat.getFont(context, getFontForType())
        elevation = resources.getDimension(getButtonElevation())
    }

    private fun getFontForType() : Int {
        return when(buttonType) {
            RentwizButtonType.LARGE -> {
                if (isEnabled) {
                    R.font.avenir_lt_std_95_black
                } else {
                    R.font.avenir_lt_pro_85_heavy
                }
            }
            RentwizButtonType.DIALOG_POSITIVE, RentwizButtonType.DIALOG_NEGATIVE, RentwizButtonType.BANNER -> {
                R.font.avenir_lt_std_95_black
            }
        }
    }

    private fun getButtonElevation(): Int {
        return when(buttonType) {
            RentwizButtonType.LARGE -> {
                if (isEnabled) {
                    getNormalButtonElevationOsSpecific()
                } else {
                    // No elevation
                    R.dimen.dimen_0dp
                }
            }
            RentwizButtonType.DIALOG_POSITIVE, RentwizButtonType.BANNER -> {
                getDialogButtonElevationOsSpecific()
            }
            RentwizButtonType.DIALOG_NEGATIVE -> {
                // No elevation
                R.dimen.dimen_0dp
            }
        }
    }

    private fun getNormalButtonElevationOsSpecific() : Int {
        return if(Utils.isAndroidPOrLater()) R.dimen.dimen_5dp else R.dimen.dimen_2dp
    }

    private fun getDialogButtonElevationOsSpecific() : Int {
        return if(Utils.isAndroidPOrLater()) R.dimen.dimen_4dp else R.dimen.dimen_1dp
    }

    private fun updateFirstEnabledStateIfNeeded() {
        updateFirstEnabledState?.let { enabled ->
            isEnabled = enabled
        }
    }
}