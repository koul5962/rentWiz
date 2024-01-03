package com.rentwiz.app.buildConfig
import com.rentwiz.app.BuildConfig
import com.rentwiz.app.BuildConfigKeys
import com.rentwiz.app.core.base.BuildConfigProvider
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BuildConfigImpl @Inject constructor(): BuildConfigProvider {

    override fun getBaseUrl(): String {
        return getValue(BuildConfigKeys.API_END_POINT)
    }

    fun getValue(key: String) : String {
        val flavorName = getFlavor()
        val flavorMap: MutableMap<String, String> = getFlavorMap(flavorName)
        val green = ""
        /*val flavorName = getFlavor()
        val value =
            configPreference.getConfigValueByKey(flavorName.uppercase(Locale.ENGLISH) + "_" + key)
        if (value != null && value != "")
            return value*/

        return flavorMap[key] ?: ""
    }

    private fun getFlavor(): String {
        return BuildConfig.FLAVOR
    }

    private fun getFlavorMap(flavorName: String): MutableMap<String, String> {
        val flavorPropertiesMap: MutableMap<String, String> by lazy {
            getFieldFromBuildConfig(
                BuildConfig::class.java,
                "${flavorName.uppercase(Locale.ENGLISH)}_MAP"
            )
        }
        return flavorPropertiesMap
    }

    @Suppress("UNCHECKED_CAST")
    private fun getFieldFromBuildConfig(
        clazz: Class<*>,
        fieldName: String
    ): MutableMap<String, String> {
        return clazz.getField(fieldName)[null] as MutableMap<String, String>
    }
}