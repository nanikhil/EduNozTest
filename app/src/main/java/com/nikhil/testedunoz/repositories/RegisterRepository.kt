package com.nikhil.testedunoz.repositories

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.nikhil.testedunoz.activities.RegisterActivity
import com.nikhil.testedunoz.constants.Variables

class RegisterRepository {
    companion object {
        @JvmStatic
        fun registerUser(email: String, password: String): MutableLiveData<Task<AuthResult>> {
            val data = MutableLiveData<Task<AuthResult>>()
            Variables.auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegisterActivity()) { task ->
                        data.value = task
                    }
            return data
        }
    }
}