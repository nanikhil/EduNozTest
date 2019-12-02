package com.nikhil.testedunoz.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.nikhil.testedunoz.repositories.LoginRepositories
import com.nikhil.testedunoz.repositories.RegisterRepository

class RegisterViewModel:ViewModel() {
    private lateinit var registerPojoLiveData: MutableLiveData<Task<AuthResult>>

    fun registerUser(email:String ,password:String): MutableLiveData<Task<AuthResult>> {
        registerPojoLiveData= RegisterRepository.registerUser(email,password)
        return registerPojoLiveData;
    }
}