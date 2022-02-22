package com.example.facebook.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.facebook.R
import com.example.facebook.activity.MainActivity
import com.example.facebook.activity.PostActivity
import com.example.facebook.model.Feed
import com.example.facebook.model.Story
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.PicassoProvider

class FeedAdapter (var context: MainActivity, var items:ArrayList<Feed>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val TYPE_ITEM_HEAD = 0
    private val TYPE_ITEM_STORY = 1
    private val TYPE_ITEM_POST = 2
    private val TYPE_ITEM_PROFILE = 3
    private val TYPE_ITEM_MULTI = 4
    private val TYPE_ITEM_LINK = 5

    override fun getItemViewType(position: Int): Int {
        val feed = items[position]
       if (feed.isHeader)
            return TYPE_ITEM_HEAD
        else if (feed.stories.size >0)
            return TYPE_ITEM_STORY
       else if (feed.link != null)
           return TYPE_ITEM_LINK
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
        else if (viewType == TYPE_ITEM_LINK){
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_post_link, parent, false)
        return PostViewHolderLink(view)
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed = items[position]

        if (holder is HeadViewHolder) {

            var tv_on_your_mind = holder.tv_on_your_mind

            tv_on_your_mind.setOnClickListener( View.OnClickListener{
                context.callPostActivity()
            })

        }
        if (holder is StoryViewHolder){

            holder.apply {
                if (adapter == null){
                    adapter = StoryAdapter(context,feed.stories)
                    recyclerView.adapter = adapter
                }
            }

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

        if (holder is PostViewHolderLink){
            holder.tv_title.text = feed.link!!.title
            holder.tv_fullname.text = feed.link!!.fullname
            holder.tv_link.text = feed.link!!.link
            holder.iv_profile.setImageResource(feed.link!!.profile)
            holder.tv_domain.text = feed.link!!.domain
            Glide.with(context).load(feed.link!!.img)
                .placeholder(R.color.white_2)
                .error(R.color.white_2)
                .into(holder.iv_link);
        }

    }

    fun addFeed(feed : Feed) {
        items.add(2,feed)
        notifyDataSetChanged()
    }

    class PostViewHolderLink(view: View) : RecyclerView.ViewHolder(view){
        var iv_link: ShapeableImageView = view.findViewById(R.id.iv_link)
        var tv_title : TextView = view.findViewById(R.id.tv_title)
        var tv_domain : TextView = view.findViewById(R.id.tv_domain)
        var tv_link : TextView = view.findViewById(R.id.tv_link)
        var tv_fullname : TextView = view.findViewById(R.id.tv_fullname)
        var iv_profile : ShapeableImageView = view.findViewById(R.id.iv_profile)

    }

    class HeadViewHolder( context: Context,view: View) : RecyclerView.ViewHolder(view){
        var tv_on_your_mind:TextView = view.findViewById(R.id.tv_on_your_mind)
    }

    class StoryViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view){
        var adapter:StoryAdapter? = null
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
    }
    class PostViewHolderMulti(view: View) : RecyclerView.ViewHolder(view){
        var iv_profile: ShapeableImageView = view.findViewById(R.id.iv_profile)
        var photo: ShapeableImageView = view.findViewById(R.id.iv_photo)
        var photo_2: ShapeableImageView = view.findViewById(R.id.iv_photo_2)
        var photo_3: ShapeableImageView = view.findViewById(R.id.iv_photo_3)
        var photo_4: ShapeableImageView = view.findViewById(R.id.iv_photo_4)
        var photo_5: ShapeableImageView = view.findViewById(R.id.iv_photo_5)
        var tv_fullname : TextView = view.findViewById(R.id.tv_fullname)

    }
}