package com.nikhil.testedunoz.activities

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.nikhil.testedunoz.R
import com.nikhil.testedunoz.adapters.NotesAdapter
import com.nikhil.testedunoz.constants.Variables
import com.nikhil.testedunoz.mvvm.NotesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_notes.view.*

class MainActivity : AppCompatActivity() {
    lateinit var context:Context
    lateinit var list: MutableList<DocumentSnapshot>
    lateinit var notesAdapter: NotesAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var progressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context=this@MainActivity
        list= mutableListOf()
        notesAdapter= NotesAdapter(context,list)
        layoutManager=LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        rvNotes.layoutManager=layoutManager
        rvNotes.adapter=notesAdapter
    }

    override fun onResume() {
        super.onResume()
        getNotes();
    }

    private fun getNotes() {
        progressDialog=Variables.showProgressDialog(context)
        progressDialog.show()

        val notesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        notesViewModel.getNotes().observe(this, Observer<QuerySnapshot>{ task->
            progressDialog.dismiss();
            if (task!=null) {
                list.clear()
                list.addAll(task.documents)
                notesAdapter.notifyDataSetChanged()
            } else {
                Variables.showToast(context,"Some error occurred")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.addnotesmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId==R.id.mnaddnotes) {
            showAddNotesDialog()
            return true
        }else if(item?.itemId==R.id.mnlogout){
            Variables.e.clear()
            Variables.e.apply()
            Variables.auth.signOut()
            startActivity(Intent(context,LoginActivity::class.java))
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAddNotesDialog() {
        val build = AlertDialog.Builder(this);
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_notes, null);
        build.setView(view)
        val okCancelDialog = build.create();

        view.btnAddNote.setOnClickListener{
            if(view.etNote.length()==0){
                Variables.showToast(context,"Please add note")
            }else {
                addNote(view.etNote.text.toString(),okCancelDialog)
            }
        }

        view.btnCancel.setOnClickListener{
                okCancelDialog.dismiss()
        }
        okCancelDialog.show();
        okCancelDialog.setCancelable(false);
    }

    private fun addNote(note: String, okCancelDialog: AlertDialog) {
        progressDialog=Variables.showProgressDialog(context)
        progressDialog.show()

        val notesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        notesViewModel.addNote(note,Variables.sh.getString("userid","")).observe(this, Observer<String>{ msg->
            progressDialog.dismiss();
            Variables.showToast(context,msg)
            okCancelDialog.dismiss()
            getNotes()
        })
    }
}