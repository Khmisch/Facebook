package com.example.facebook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook.R
import com.example.facebook.model.Story
import com.google.android.material.imageview.ShapeableImageView

class StoryAdapter(context: Context, var items:ArrayList<Story>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val TYPE_ITEM_STORY = 1
    private val TYPE_ITEM_CREATE = 2


    override fun getItemViewType(position: Int): Int {
        val feed = items[position]
        if (feed.isCeate)
            return TYPE_ITEM_CREATE
        return TYPE_ITEM_STORY
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_CREATE){
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story_view_create, parent, false)
        return StoryViewHolderCreate(view)}
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story_view, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed = items[position]

        if (holder is StoryViewHolder){
            var iv_profile =holder.iv_profile
            var tv_fullname =holder.tv_fullname
            var iv_background =holder.iv_background

            iv_background.setImageResource(feed.background)
            iv_profile.setImageResource(feed.profile)
            tv_fullname.text = feed.fullname
        }
        if (holder is StoryViewHolderCreate){
            var iv_background =holder.iv_background

            iv_background.setImageResource(feed.background)

        }
    }

    class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var iv_profile: ShapeableImageView = view.findViewById(R.id.iv_profile)
        var iv_background: ShapeableImageView = view.findViewById(R.id.iv_background)
        var tv_fullname : TextView = view.findViewById(R.id.tv_fullname)

    }
    class StoryViewHolderCreate(view: View) : RecyclerView.ViewHolder(view){
        var iv_background: ShapeableImageView = view.findViewById(R.id.iv_background)

    }
}