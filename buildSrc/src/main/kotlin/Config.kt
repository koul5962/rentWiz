object Config {
    private const val FLAVOR_PROD = "prod"
    private const val FLAVOR_QA = "qa"
    private const val FLAOUR_DEV = "dev"

    val defaultFlavors = listOf(FLAVOR_PROD, FLAVOR_QA, FLAOUR_DEV)

    val variantFieldMap = mapOf(
        FLAVOR_QA to getQaFields(),
        FLAVOR_PROD to getProdFields(),
        FLAOUR_DEV to getDevFields()
    )

    private fun getQaFields(): Map<String, String> {
        val fields = mutableMapOf<String, String>()
        fields["API_END_POINT"] = "https://95b91c4d-f9b7-4265-bd48-7c7db91e0b19.mock.pstmn.io/"
        fields["API_KEY"] = ""
        return fields
    }

    private fun getProdFields(): Map<String, String> {
        val fields = mutableMapOf<String, String>()
        fields["API_END_POINT"] = "https://019f0ec4-70e4-4d77-a1a6-8ba3dfe01237.mock.pstmn.io/"
        fields["API_KEY"] = ""
        return fields
    }

    private fun getDevFields(): Map<String, String> {
        val fields = mutableMapOf<String, String>()
        fields["API_END_POINT"] = "https://7222e3e5-b046-47b8-8f63-a262c8a4558f.mock.pstmn.io/"
        fields["API_KEY"] = ""
        return fields
    }

    fun getKeys(): Set<String> {
        val keys = mutableSetOf<String>()
        variantFieldMap.forEach {
            it.value.forEach { entry ->
                keys.add(entry.key)
            }
        }
        return keys
    }

    fun getFlavorList(): String {
        val flavorBuilder = StringBuilder()
        defaultFlavors.forEach {
            flavorBuilder.append("add(\"$it\");")
        }
        return "new java.util.ArrayList() {{$flavorBuilder}}"
    }

    fun getVariantFields(flavor: String): String {
        val fields = variantFieldMap[flavor]
        val fieldsBuilder = StringBuilder("")
        fields?.forEach { entry ->
            fieldsBuilder.append("put(\"${entry.key}\",\"${entry.value}\");")
        }
        return "new java.util.HashMap() {{$fieldsBuilder}}"
    }
}