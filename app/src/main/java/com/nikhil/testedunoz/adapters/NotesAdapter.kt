package com.nikhil.testedunoz.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.nikhil.testedunoz.R
import kotlinx.android.synthetic.main.list_notes.view.*

class NotesAdapter(private var context: Context, private var list: MutableList<DocumentSnapshot>) :
        RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.list_notes,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return if (list.isNotEmpty())
            list.size
        else
            0
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemView.tvNote.text=list.get(position).get("note").toString()
    }
}