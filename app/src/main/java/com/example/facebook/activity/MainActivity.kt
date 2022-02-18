package com.example.facebook.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook.R
import com.example.facebook.adapter.FeedAdapter
import com.example.facebook.model.Feed
import com.example.facebook.model.Post
import com.example.facebook.model.Story

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(GridLayoutManager(this, 1))

        refreshFeedAdapter(getAllFeeds())
    }


    private fun refreshFeedAdapter(allFeeds: ArrayList<Feed>) {
        val adapter = FeedAdapter(this, allFeeds)
        recyclerView!!.adapter = adapter
    }

    private fun getAllFeeds(): ArrayList<Feed> {
        val stories: ArrayList<Story> = ArrayList<Story>()

        stories.add(Story( R.drawable.ic_create,  R.drawable.im_sample_007, "Khurshidbek", true))
        stories.add(Story(R.drawable.my_profile, R.drawable.im_flowers, "Rachel"))
        stories.add(Story(R.drawable.im_person_00,R.drawable.im_stories_feynamn, "Feynman"))
        stories.add(Story(R.drawable.im_sample_007, R.drawable.im_user_3, "Khurshidbek"))
        stories.add(Story(R.drawable.my_profile, R.drawable.im_flowers, "Rachel"))
        stories.add(Story(R.drawable.im_person_00,R.drawable.im_stories_feynamn, "Feynman"))
        stories.add(Story(R.drawable.im_sample_007, R.drawable.im_sample_007, "Khurshidbek"))


        val feed: ArrayList<Feed> = ArrayList<Feed>()
        //Head
        feed.add(Feed())
        //Story
        feed.add(Feed(stories))
        //Post

        feed.add(Feed(Post(R.drawable.im_sample_007, "Khurshidbek Kurbanov", R.drawable.im_post_4)))
        feed.add(Feed(Post(R.drawable.my_profile, "Rachel ", R.drawable.im_post_3,R.drawable.im_user_3,R.drawable.im_post_1,R.drawable.im_post_4,R.drawable.im_sample_007, false, true)))
        feed.add(Feed(Post(R.drawable.im_person_00, "Richard Feynman", R.drawable.im_user_3)))
        feed.add(Feed(Post(R.drawable.im_sample_007, "Khurshidbek Kurbanov", R.drawable.im_post_1)))
        feed.add(Feed(Post(R.drawable.my_profile, "Rachel", R.drawable.im_stories_holiday)))
        feed.add(Feed(Post(R.drawable.im_sample_007, "Khurshidbek Kurbanov", R.drawable.im_sample_007, true)))
        feed.add(Feed(Post(R.drawable.im_person_00, "Richard Feynman", R.drawable.im_user_3)))
        feed.add(Feed(Post(R.drawable.my_profile, "Rachel", R.drawable.im_stories_holiday)))



        return feed
    }
}