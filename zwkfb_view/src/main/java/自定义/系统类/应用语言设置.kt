@file:Suppress("DEPRECATION")

package 自定义.系统类

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import android.preference.PreferenceManager
import android.provider.Settings
import androidx.core.content.edit
import java.util.Locale

object 应用语言设置 {

    /* 保存用户手动选择的语言，空串表示“跟随系统” */
    fun 置应用语言(上下文: Activity?, 语言: String) {
        PreferenceManager.getDefaultSharedPreferences(上下文)
            .edit { putString("应用语言配置", 语言) }
        上下文!!.recreate()
    }

    /* 读取保存的语言，可能为空串 */
    fun 取应用语言(上下文: Context?): String =
        PreferenceManager.getDefaultSharedPreferences(上下文)
            .getString("应用语言配置", "") ?: ""

    fun 打开系统设置语言界面(上下文: Context?) {
        val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
        上下文?.startActivity(intent)
    }

    /* 包装一个新的 Context */
    fun 封装上下文(上下文: Context?): Context {
        val 语言 = 取应用语言(上下文)
        val 地区 = when {
            语言.isBlank() -> 取系统语言() // 跟随系统    isBlank()：是否为空或空格，空串或空白串
            else -> Locale.forLanguageTag(语言) // 手动
        }
        val 配置 = Configuration().apply {
            setLocale(地区)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setLocales(LocaleList(地区))
            }
        }
        return 上下文!!.createConfigurationContext(配置)
    }

    /* 获取系统首选语言 */
    private fun 取系统语言(): Locale =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // >= Android 7.0
            Resources.getSystem().configuration.locales[0]
        } else { // < Android 7.0
            Resources.getSystem().configuration.locale
        }

}
