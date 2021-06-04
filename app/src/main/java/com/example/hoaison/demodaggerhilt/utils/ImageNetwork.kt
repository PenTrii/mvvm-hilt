package com.example.hoaison.demodaggerhilt.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions
import com.example.hoaison.demodaggerhilt.R

@SuppressLint("StaticFieldLeak")
object ImageNetwork {
    @SuppressLint("StaticFieldLeak")
    private lateinit var context: Context
    private var options: RequestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .centerCrop()

    private lateinit var glide: RequestManager

    fun with(context: Context): ImageNetwork {
        this.context = context
        glide = Glide.with(context).applyDefaultRequestOptions(options)
        return this
    }

    @SuppressLint("CheckResult")
    fun progressDrawable(boolean: Boolean): ImageNetwork {
        if (boolean) {
            val progressDrawable = CircularProgressDrawable(context)
            progressDrawable.strokeWidth = 5f
            progressDrawable.centerRadius = 30f
            progressDrawable.setColorSchemeColors(context.getColor(R.color.colorAccent))
            progressDrawable.start()

            options.placeholder(progressDrawable)
            glide = glide.applyDefaultRequestOptions(options)
        }
        return this
    }

    fun url(url: String): RequestBuilder<Drawable> {
        val cacheUrl = DataStorage.cacheImage(context, url)
        return glide.load(cacheUrl)
    }

    fun load(model: GlideUrl): RequestBuilder<Drawable> {
        return glide.load(model)
    }
}


private const val CHECK_INSTALL = "check_install"
private const val CACHE_IMAGE = "cache_image"
object DataStorage {
    private const val TAG: String = "DataStorage"

    fun checkInstallApp(context: Context) {
        Log.e(TAG, "Check Install App???")
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(CHECK_INSTALL, Context.MODE_PRIVATE)
        if (sharedPref.getBoolean(CHECK_INSTALL, true)) {
            sharedPref.edit().putBoolean(CHECK_INSTALL, false).apply()
        }
    }

    fun cacheImage(context: Context, urlImage: String?): String? {
        var newUrlImage = urlImage
        if (newUrlImage.isNullOrEmpty()) return null
        val cacheVariable = "?time="
        val prefs = context.getSharedPreferences(CACHE_IMAGE, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()
        val url: String = newUrlImage
        if (url.contains(cacheVariable)) {
            val arraySplit =
                newUrlImage.split("\\?time=".toRegex()).toTypedArray()
            prefsEdit.putString(arraySplit[0], arraySplit[1])
            prefsEdit.apply()
        } else {
            val cacheValue = prefs.getString(newUrlImage, null)
            if (!cacheValue.isNullOrEmpty()) {
                newUrlImage += cacheVariable + cacheValue
            }
        }
        return newUrlImage
    }

}