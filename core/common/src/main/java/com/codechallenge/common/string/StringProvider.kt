package com.codechallenge.common.string

import android.content.Context
import androidx.annotation.StringRes

interface StringProvider {
    fun getString(@StringRes resId: Int): String
}

class AppStringProvider(private val context: Context) : StringProvider {

    override fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }
}