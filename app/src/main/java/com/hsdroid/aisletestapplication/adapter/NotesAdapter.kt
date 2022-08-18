package com.hsdroid.aisletestapplication.adapter

import Profiles
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.hsdroid.aisletestapplication.databinding.LayoutItemNoteBinding


class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    lateinit var context: Context
    lateinit var data: List<Profiles>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val profile: Profiles = data[position]
        val name = profile.first_name
        val photo = profile.avatar

        Glide.with(context)
            .load(photo)
            .override(20, 20)
            .into(holder.dataBinding.imageView)

        holder.dataBinding.name.text = name
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class NotesViewHolder(itemView: LayoutItemNoteBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        lateinit var dataBinding: LayoutItemNoteBinding

        init {
            dataBinding = itemView
        }
    }


}