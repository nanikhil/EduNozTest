package com.nikhil.testedunoz.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.QuerySnapshot
import com.nikhil.testedunoz.activities.RegisterActivity
import com.nikhil.testedunoz.constants.Variables
import com.nikhil.testedunoz.pojo.Note

class NotesRepository {

    companion object {
        @JvmStatic
        fun addNote(note: String, user: String): MutableLiveData<String> {
            val data = MutableLiveData<String>()
            val notes = Note(note, user)
            Variables.db.collection("notes")
                    .add(notes)
                    .addOnSuccessListener {
                        data.value = "Note added successfully";
                    }
                    .addOnFailureListener {
                        data.value = "Notes not Added"
                    }
            return data
        }

        @JvmStatic
        fun getNotes(): MutableLiveData<QuerySnapshot> {
            val data = MutableLiveData<QuerySnapshot>()
            val notes = Variables.db.collection("notes").get()
            notes
                    .addOnSuccessListener { result ->
                        data.value = result
                    }
                    .addOnFailureListener { exception ->
                        data.value = null
                    }
            return data
        }
    }
}