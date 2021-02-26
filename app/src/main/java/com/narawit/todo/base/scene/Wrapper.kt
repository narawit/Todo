package com.narawit.todo.base.scene

import android.annotation.TargetApi
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import java.util.*


class Wrapper(base: Context) : ContextWrapper(base) {
    companion object {

        private fun adjustFontScale(config: Configuration, font: Float) {
            config.fontScale = font
        }

        private fun changeLanguage(config: Configuration, language: String) {
            val sysLocale: Locale = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
                getSystemLocale(
                    config
                )
            } else {
                getSystemLocaleLegacy(
                    config
                )
            }
            if (language != "" && sysLocale?.language != language) {
                val locale = Locale(language)
                Locale.setDefault(locale)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                    setSystemLocale(
                        config,
                        locale
                    )
                } else {
                    setSystemLocaleLegacy(
                        config,
                        locale
                    )
                }

            }
        }

        fun wrap(context: Context, language: String, font: Float): ContextWrapper {
            val config = context.resources.configuration
            val display = context.resources.displayMetrics
            changeLanguage(
                config,
                language
            )
            adjustFontScale(
                config,
                font
            )
            return Wrapper(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
                    context.createConfigurationContext(config)
                else {
                    context.resources.updateConfiguration(config, display)
                    context
                }
            )
        }

        @SuppressWarnings("deprecation")
        fun getSystemLocaleLegacy(config: Configuration): Locale {
            return config.locale
        }

        @TargetApi(Build.VERSION_CODES.N_MR1)
        fun getSystemLocale(config: Configuration): Locale {
            return config.locales.get(0)
        }

        @SuppressWarnings("deprecation")
        fun setSystemLocaleLegacy(config: Configuration, locale: Locale) {
            config.locale = locale
        }

        @TargetApi(Build.VERSION_CODES.N_MR1)
        fun setSystemLocale(config: Configuration, locale: Locale) {
            config.setLocale(locale)
        }
    }
}