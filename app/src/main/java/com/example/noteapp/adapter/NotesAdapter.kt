package com.example.noteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.database.model.Notes
import com.example.noteapp.extensions.convertDateToFormat
import com.example.noteapp.extensions.gone
import com.example.noteapp.extensions.visible
import com.example.noteapp.utils.load

/**
 * The NotesAdapter.kt to populate the recyclerview
 * @author Malik Dawar, malikdawar@hotmail.com
 */
class NotesAdapter(
    private val noteItemClickListener: NoteItemClickListener,
    private val notesList: List<Notes>
) : RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //creating views
        var mainLayout = view.findViewById(R.id.mainLayout) as CardView
        var imgView = view.findViewById(R.id.imgView) as ImageView
        var tvTitle = view.findViewById(R.id.tvTitle) as TextView
        var tvDetails = view.findViewById(R.id.tvDetails) as TextView
        var tvDate = view.findViewById(R.id.tvDate) as TextView
        var tvUpdatedTag = view.findViewById(R.id.tvUpdatedTag) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //returning the View Holder
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_notes_ui, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //populating the data from list to view
        val note = notesList[position]
        note.run {
            holder.tvTitle.text = title
            holder.tvDetails.text = description
            holder.imgView.load(image)
            holder.tvDate.text = date?.convertDateToFormat()

            if (isUpdated)
                holder.tvUpdatedTag.visible()
            else
                holder.tvUpdatedTag.gone()

            //attaching the onCLick to the layout
            holder.mainLayout.setOnClickListener {
                noteItemClickListener.onItemClickListener(this)
            }
        }
    }

    //interface to get the callback
    interface NoteItemClickListener {
        fun onItemClickListener(note: Notes)
    }
}