package com.nikhil.testedunoz.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.QuerySnapshot
import com.nikhil.testedunoz.repositories.LoginRepositories
import com.nikhil.testedunoz.repositories.NotesRepository

class NotesViewModel:ViewModel(){
    private lateinit var notesLiveData: MutableLiveData<QuerySnapshot>
    private lateinit var addNotesLiveData: MutableLiveData<String>

    fun getNotes(): MutableLiveData<QuerySnapshot> {
        notesLiveData= NotesRepository.getNotes()
        return notesLiveData;
    }

    fun addNote(note:String,user:String):MutableLiveData<String>{
        addNotesLiveData=NotesRepository.addNote(note,user)
        return addNotesLiveData
    }

}