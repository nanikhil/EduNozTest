package com.nikhil.testedunoz.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.nikhil.testedunoz.activities.LoginActivity
import com.nikhil.testedunoz.repositories.LoginRepositories


class LoginViewModel:ViewModel(){
    private lateinit var loginPojoLiveData: MutableLiveData<Task<AuthResult>>

    fun getUser(email:String ,password:String): MutableLiveData<Task<AuthResult>> {
        loginPojoLiveData= LoginRepositories.getLoginDetails(email,password)
        return loginPojoLiveData;
    }

}