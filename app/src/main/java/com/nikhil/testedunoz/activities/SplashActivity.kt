package com.nikhil.testedunoz.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.nikhil.testedunoz.R
import com.nikhil.testedunoz.constants.Variables


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Variables.sh=getPreferences(Context.MODE_PRIVATE)
        Variables.e=Variables.sh.edit()
        Variables.e.apply()
        Variables.auth= FirebaseAuth.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(false)
                .build()
        Variables.db= FirebaseFirestore.getInstance()
        Variables.db.setFirestoreSettings(settings)
        Handler().postDelayed(Runnable {
            if(Variables.sh.getString("userid",null)==null)
                startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
            else
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
            finish()
        },3000)
    }
}
