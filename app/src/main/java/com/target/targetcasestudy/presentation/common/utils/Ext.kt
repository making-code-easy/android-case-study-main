package com.target.targetcasestudy.presentation.common.utils

import android.content.res.Resources
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> LifecycleOwner.collectLatestLifecycleFlow(f: Flow<T>, block: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            f.collectLatest(block)
        }
    }
}

fun AppCompatImageView.loadImage(imageUrl: String) {
    Glide.with(this.context).load(imageUrl).into(this)
}

val Int.toDP: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()