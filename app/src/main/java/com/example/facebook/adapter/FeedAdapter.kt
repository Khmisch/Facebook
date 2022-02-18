package com.example.facebook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook.R
import com.example.facebook.model.Feed
import com.example.facebook.model.Story
import com.google.android.material.imageview.ShapeableImageView

class FeedAdapter (var context: Context, var items:ArrayList<Feed>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val TYPE_ITEM_HEAD = 0
    private val TYPE_ITEM_STORY = 1
    private val TYPE_ITEM_POST = 2
    private val TYPE_ITEM_PROFILE = 3
    private val TYPE_ITEM_MULTI = 4

    override fun getItemViewType(position: Int): Int {
        val feed = items[position]
       if (feed.isHeader)
            return TYPE_ITEM_HEAD
        else if (feed.stories.size >0)
            return TYPE_ITEM_STORY
       else if (feed.post!!.isProfile)
            return TYPE_ITEM_PROFILE
       else if (feed.post!!.isMultiple)
            return TYPE_ITEM_MULTI
        return TYPE_ITEM_POST
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_HEAD){
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_head, parent, false)
        return HeadViewHolder(context, view)
        } else if (viewType == TYPE_ITEM_STORY){
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_story, parent, false)
        return StoryViewHolder(context,view)
        }else if (viewType == TYPE_ITEM_PROFILE){
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_post_profile, parent, false)
        return PostViewHolderProfile(view)
        }else if (viewType == TYPE_ITEM_MULTI){
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_post_multiple, parent, false)
        return PostViewHolderMulti(view)
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed = items[position]

        if (holder is HeadViewHolder) {

        }
        if (holder is StoryViewHolder){
            var recyclerView =holder.recyclerView
           refreshAdapter(feed.stories, recyclerView)
        }
        if (holder is PostViewHolder){
            var iv_profile =holder.iv_profile
            var photo =holder.photo
            var tv_fullname =holder.tv_fullname

            iv_profile.setImageResource(feed.post!!.profile)
            photo.setImageResource(feed.post!!.photo)
            tv_fullname.text = feed.post?.fullname
        }
        if (holder is PostViewHolderProfile){
            var iv_profile =holder.iv_profile
            var photo =holder.photo
            var tv_fullname =holder.tv_fullname

            iv_profile.setImageResource(feed.post!!.profile)
            photo.setImageResource(feed.post!!.photo)
            tv_fullname.text = feed.post?.fullname
        }
        if (holder is PostViewHolderMulti){
            var iv_profile =holder.iv_profile
            var photo =holder.photo
            var photo_2 =holder.photo_2
            var photo_3 =holder.photo_3
            var photo_4 =holder.photo_4
            var photo_5 =holder.photo_5
            var tv_fullname =holder.tv_fullname

            iv_profile.setImageResource(feed.post!!.profile)
            photo.setImageResource(feed.post!!.photo)
            photo_2.setImageResource(feed.post!!.photo_2)
            photo_3.setImageResource(feed.post!!.photo_3)
            photo_4.setImageResource(feed.post!!.photo_4)
            photo_5.setImageResource(feed.post!!.photo_5)
            tv_fullname.text = feed.post?.fullname
        }

    }

    private fun refreshAdapter(stories: ArrayList<Story>, recyclerView:RecyclerView) {
        val adapter = StoryAdapter(context, stories)
        recyclerView!!.adapter = adapter
    }


    class HeadViewHolder(context: Context,view: View) : RecyclerView.ViewHolder(view){
    }

    class StoryViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view){
        var recyclerView: RecyclerView

        init {
            recyclerView = view.findViewById(R.id.recyclerView)
            recyclerView.setLayoutManager(LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false ))
        }

    }
    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var iv_profile: ShapeableImageView = view.findViewById(R.id.iv_profile)
        var photo: ShapeableImageView = view.findViewById(R.id.iv_photo)
        var tv_fullname : TextView = view.findViewById(R.id.tv_fullname)

    }
    class PostViewHolderProfile(view: View) : RecyclerView.ViewHolder(view){
        var iv_profile: ShapeableImageView = view.findViewById(R.id.iv_profile)
        var photo: ShapeableImageView = view.findViewById(R.id.iv_photo)
        var tv_fullname : TextView = view.findViewById(R.id.tv_fullname)

    }class PostViewHolderMulti(view: View) : RecyclerView.ViewHolder(view){
        var iv_profile: ShapeableImageView = view.findViewById(R.id.iv_profile)
        var photo: ShapeableImageView = view.findViewById(R.id.iv_photo)
        var photo_2: ShapeableImageView = view.findViewById(R.id.iv_photo_2)
        var photo_3: ShapeableImageView = view.findViewById(R.id.iv_photo_3)
        var photo_4: ShapeableImageView = view.findViewById(R.id.iv_photo_4)
        var photo_5: ShapeableImageView = view.findViewById(R.id.iv_photo_5)
        var tv_fullname : TextView = view.findViewById(R.id.tv_fullname)

    }
}