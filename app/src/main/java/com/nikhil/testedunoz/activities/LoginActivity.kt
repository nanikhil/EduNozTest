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
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    lateinit var context: Context
    lateinit var progressDialog: Dialog
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        context=this@LoginActivity

        btnLogin.setOnClickListener {
            if(Variables.isNetworkAvailable(context)){
                if(!Variables.isValidMail(etLoginEmail.text.toString()))
                {
                    Variables.showToast(context,"Please enter valid email")
                }else if(etLoginPassword.length()==0)
                {
                    Variables.showToast(context,"Please enter password")
                }else{
                    SignInUser()
                }
            }
        }

        tvRegister.setOnClickListener {
            startActivity(Intent(context,RegisterActivity::class.java))
        }
    }

    private fun SignInUser() {
        progressDialog=Variables.showProgressDialog(context)
        progressDialog.show()

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.getUser(etLoginEmail.text.toString(), etLoginPassword.text.toString()).observe(this, Observer<Task<AuthResult>>{task->
            progressDialog.dismiss();
            if (task.isSuccessful) {
                Variables.e.putString("userid",Variables.auth.currentUser?.uid)
                Variables.e.apply()
                startActivity(Intent(context,MainActivity::class.java))
                finish()
            } else {
                Variables.showToast(context,"Wrong email or password")
            }
        })
    }
}
