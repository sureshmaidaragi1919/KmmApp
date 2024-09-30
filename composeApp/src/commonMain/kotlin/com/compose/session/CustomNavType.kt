package com.compose.session

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class Test(val name: String)
object CustomNavType {

    val ContentType = object : NavType<Content>(
        isNullableAllowed = false
    ) {
        override fun serializeAsValue(value: Content): String {
            return super.serializeAsValue(value)
        }

        override fun get(bundle: Bundle, key: String): Content? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Content {
            return Json.decodeFromString(value)
        }

        override fun put(bundle: Bundle, key: String, value: Content) {
            bundle.putString(key, Json.encodeToString(value))
        }

    }
}