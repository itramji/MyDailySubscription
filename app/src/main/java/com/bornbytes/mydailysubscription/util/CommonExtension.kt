package com.bornbytes.mydailysubscription.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.ConnectivityManager
import android.util.Patterns
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Context?.isOnline(): Boolean {
    this?.apply {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
    return false
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

fun View.getString(@StringRes resId: Int): String = resources.getString(resId)

fun View.getString(@StringRes resId: Int, vararg formatArgs: Any): String = resources.getString(resId, *formatArgs)

fun View.getDimenSize(@DimenRes resId: Int): Int = resources.getDimensionPixelSize(resId)

fun View.isVisible() = visibility == View.VISIBLE

fun View.isInvisible() = visibility == View.INVISIBLE

fun View.isGone() = visibility == View.GONE

fun View.toggleVisibility(): View {
    if (visibility == View.VISIBLE) {
        visibility = View.INVISIBLE
    } else {
        visibility = View.INVISIBLE
    }
    return this
}

/**
 * Extension method to get a view as bitmap.
 */
fun View.getBitmap(): Bitmap {
    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bmp)
    draw(canvas)
    canvas.save()
    return bmp
}

inline fun <T : View> T.afterMeasured(crossinline block: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                block()
            }
        }
    })
}

val EditText.value
    get() = text?.toString() ?: ""

fun EditText.isEmpty() = value.isEmpty()

fun Activity.startActivity(cls: Class<*>, finishCallingActivity: Boolean = false) {
    val intent = Intent(this, cls)
    startActivity(intent)
    if (finishCallingActivity) {
        finish()
    }
}

fun FragmentManager.replaceFragment(resId: Int, fragment: Fragment) = this.beginTransaction().replace(resId, fragment).commitNow()

//Email Validation
fun String.isValidEmail(): Boolean = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

//Phone number format
/*fun String.formatPhoneNumber(context: Context, region: String): String? {
    val phoneNumberKit = PhoneNumberUtil.createInstance(context)
    val number = phoneNumberKit.parse(this, region)
    if (!phoneNumberKit.isValidNumber(number))
        return null

    return phoneNumberKit.format(number, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
}*/

//Extensions
fun Context.getColor(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)
fun Context.getDrawable(@DrawableRes resId: Int) = ContextCompat.getDrawable(this, resId)
