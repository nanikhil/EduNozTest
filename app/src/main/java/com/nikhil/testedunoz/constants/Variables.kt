package com.nikhil.testedunoz.constants

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.Window
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nikhil.testedunoz.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class Variables {
    companion object {
        val EMAIL_ADDRESS_PATTERN = Pattern
                .compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                        + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
                        + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+")
        @JvmStatic lateinit var sh: SharedPreferences
        @JvmStatic lateinit var e: SharedPreferences.Editor
        @JvmStatic lateinit var auth: FirebaseAuth
        @JvmStatic lateinit var db: FirebaseFirestore
        //check email is valid or not
        @JvmStatic
        fun isValidMail(email: String?): Boolean {
            return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
        }

        @JvmStatic
        //show toast messages
        fun showToast(context: Context?, message: String?) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        @JvmStatic
        //check network availability
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity == null) {
                return false
            } else {
                val info = connectivity.allNetworkInfo
                if (info != null) {
                    for (i in info.indices) {
                        if (info[i].state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
                    }
                }
            }
            return false
        }

        @JvmStatic
        //show progress dialog
        fun showProgressDialog(context: Context?): Dialog {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(R.layout.vw_custom_progress_bar)
            dialog.setCancelable(false)
            // dialog.show();
            return dialog
        }
    }
}