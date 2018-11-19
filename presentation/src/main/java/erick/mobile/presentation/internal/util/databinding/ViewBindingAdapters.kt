package erick.mobile.presentation.internal.util.databinding

import android.databinding.BindingAdapter
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import erick.mobile.presentation.internal.util.fade

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("fadeView")
    fun fadeView(view: View, show: Boolean) {
        view.fade(show)
    }

    @JvmStatic
    @BindingAdapter("visible")
    fun setVisible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("showLongMessage", "callback", requireAll = false)
    fun showLongMessage(view: View, text: String?, callback: BaseTransientBottomBar.BaseCallback<Snackbar>? = null) {
        text?.let {
            val snackbar = Snackbar.make(view, it, Snackbar.LENGTH_LONG)
            if (callback != null) snackbar.addCallback(callback)
            snackbar.show()
        }
    }

    @JvmStatic
    @BindingAdapter("loadUrl")
    fun loadUrl(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView.context)
                .load(it)
                .apply(RequestOptions.noTransformation())
                .into(imageView)
        }
    }
}