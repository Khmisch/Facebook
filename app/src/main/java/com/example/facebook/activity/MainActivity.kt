package com.example.facebook.activity

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook.R
import com.example.facebook.adapter.FeedAdapter
import com.example.facebook.model.Feed
import com.example.facebook.model.Link
import com.example.facebook.model.Post
import com.example.facebook.model.Story

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FeedAdapter

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
        adapter = FeedAdapter(this, allFeeds)
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

    fun callPostActivity() {
        var intent = Intent(this, PostActivity::class.java)
        postLauncher.launch(intent)
    }
    private var postLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            var link = data!!.getSerializableExtra("link") as Link
            Log.d("@@@", link.toString())
            var feed = Feed(link)
            adapter.addFeed(feed)

        }
    }

}