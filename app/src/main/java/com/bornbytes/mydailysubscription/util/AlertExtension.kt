package com.bornbytes.mydailysubscription.util

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

// Show alert dialog
fun Context.showAlertDialog(positiveButtonLabel: String = "Okay", title: String, message: String, actionOnPositiveButton: () -> Unit) {
    val builder = AlertDialog.Builder(this).setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(positiveButtonLabel) { dialog, id ->
            dialog.cancel()
            actionOnPositiveButton()
        }
    val alert = builder.create()
    alert?.show()
}

// Toast extensions
fun Context.showShotToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

// SnackBar Extensions
fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun View.showLongSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun View.snackBarWithAction(message: String, actionable: String, block: () -> Unit) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).setAction(actionable) {
            block()
        }.show()
}
