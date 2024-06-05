package com.gmail.vexonelite.jetpack.study


import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.stringResource


/**
 * * [Kotlin’s Sealed Interfaces in Android](https://proandroiddev.com/kotlins-sealed-interfaces-in-android-0882a3d2afd1)
 */
@Stable
sealed interface StringResource {
    fun resolve(context: Context): String

    data class Text(val text: String): StringResource {
        override fun resolve(context: Context): String {
            return text
        }
    }

    data class ResId(@StringRes val stringId: Int): StringResource {
        override fun resolve(context: Context): String {
            return context.getString(stringId)
        }
    }

    data class ResIdWithParams(@StringRes val stringId: Int, val params: List<Any>): StringResource {
        override fun resolve(context: Context): String {
            return context.getString(stringId, *params.toTypedArray())
        }
    }

    @Composable
    fun resolve(): String {
        return when (this) {
            is ResId -> stringResource(id = stringId)
            is ResIdWithParams -> stringResource(id = stringId, *params.toTypedArray())
            is Text -> text
        }
    }
}

