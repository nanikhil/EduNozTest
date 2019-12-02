package com.nikhil.testedunoz.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.nikhil.testedunoz.R
import com.nikhil.testedunoz.constants.Variables
import com.nikhil.testedunoz.mvvm.LoginViewModel
import com.nikhil.testedunoz.mvvm.RegisterViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var context: Context
    lateinit var progressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        context=this@RegisterActivity

        btnRegister.setOnClickListener {
            if(Variables.isNetworkAvailable(context)){
                if(!Variables.isValidMail(etRegisterEmail.text.toString()))
                {
                    Variables.showToast(context,"Please enter valid email")
                }else if(etRegisterPassword.length()==0)
                {
                    Variables.showToast(context,"Please enter password")
                }else{
                    SignUpUser()
                }
            }
        }

        tvLogin.setOnClickListener {
            startActivity(Intent(context,LoginActivity::class.java))
        }
    }

    private fun SignUpUser() {
        progressDialog=Variables.showProgressDialog(context)
        progressDialog.show()

        val registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        registerViewModel.registerUser(etRegisterEmail.text.toString(), etRegisterPassword.text.toString()).observe(this, Observer<Task<AuthResult>>{ task->
            progressDialog.dismiss();
            if (task.isSuccessful) {
                Variables.e.putString("userid",Variables.auth.currentUser?.uid)
                Variables.e.apply()
                startActivity(Intent(context,MainActivity::class.java))
                finish()
            } else {
                Variables.showToast(context,"Registration fail")
            }
        })
    }
}