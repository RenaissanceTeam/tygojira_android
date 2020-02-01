package android.view

import android.graphics.Rect
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Created by 23alot on 01.02.2020.
 * @see https://gitlab.com/terrakok/gitlab-client/blob/develop/app/src/main/java/ru/terrakok/gitlabclient/util/Extensions.kt
 * by terrakok
 */

fun View.doOnApplyWindowInsets(block: (View, insets: WindowInsetsCompat, initialPadding: Rect) -> WindowInsetsCompat) {
    val initialPadding = recordInitialPaddingForView(this)
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        block(v, insets, initialPadding)
    }
    requestApplyInsetsWhenAttached()
}

private fun recordInitialPaddingForView(view: View) =
    Rect(view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)

private fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        ViewCompat.requestApplyInsets(this)
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                ViewCompat.requestApplyInsets(v)
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

