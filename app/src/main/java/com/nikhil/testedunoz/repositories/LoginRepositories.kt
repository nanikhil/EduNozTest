package com.nikhil.testedunoz.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.nikhil.testedunoz.activities.LoginActivity
import com.nikhil.testedunoz.constants.Variables

class LoginRepositories {
    companion object {
        @JvmStatic
        fun getLoginDetails(email: String, password: String): MutableLiveData<Task<AuthResult>> {
            val data = MutableLiveData<Task<AuthResult>>()
            Variables.auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity()) { task ->
                        data.value = task
                    }
            return data;
        }
    }
}