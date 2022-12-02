package edu.utap.breakfastdishgenerator.glide

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.firebase.storage.StorageReference
import edu.utap.breakfastdishgenerator.R
import java.io.InputStream


@GlideModule
class AppGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setLogLevel(Log.ERROR)
    }
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        // Register FirebaseImageLoader to handle StorageReference
        registry.append(
            StorageReference::class.java, InputStream::class.java,
            FirebaseImageLoader.Factory()
        )
    }
}

object Glide {
    private val width = Resources.getSystem().displayMetrics.widthPixels
    private val height = Resources.getSystem().displayMetrics.heightPixels
    private var glideOptions = RequestOptions ()
        .fitCenter()
        .transform(RoundedCorners (20))

    private fun fromHtml(source: String): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            @Suppress("DEPRECATION")
            return Html.fromHtml(source).toString()
        }
    }

    fun glideFetch(urlString: String, thumbnailURL: String, imageView: ImageView) {
        GlideApp.with(imageView.context)
            .asBitmap() // Try to display animated Gifs and video still
            .load(fromHtml(urlString))
            .apply(glideOptions)
            .error(R.color.colorAccent)
            .override(width, height)
            .error(
                GlideApp.with(imageView.context)
                    .asBitmap()
                    .load(fromHtml(thumbnailURL))
                    .apply(glideOptions)
                    .error(R.color.colorAccent)
                    .override(500, 500)
            )
            .into(imageView)
    }


    fun fetch(storageReference: StorageReference, imageView: ImageView) {
        val width = 600
        val height = 600
        GlideApp.with(imageView.context)
            .asBitmap()
            .load(storageReference)
            .apply(glideOptions)
            .error(android.R.color.holo_red_dark)
            .override(width, height)
            .into(imageView)
    }
}
