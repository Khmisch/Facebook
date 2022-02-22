package com.example.facebook.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.facebook.R
import com.example.facebook.helper.Utils
import com.example.facebook.model.Feed
import com.example.facebook.model.Link
import com.example.facebook.model.Post
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException


class PostActivity : AppCompatActivity() {

    lateinit var editText:EditText
    lateinit var iv_post:ImageView
    lateinit var iv_delete:ImageView
    lateinit var tv_link_domain:TextView
    lateinit var tv_title:TextView
    lateinit var iv_exit:ImageView
    lateinit var ll_preview:LinearLayout
    lateinit var bt_post:Button
    private lateinit var la_loading: LottieAnimationView
    private var isFindLink = false
    lateinit var link:Link

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        initViews()
    }

    private fun initViews() {
        ll_preview = findViewById(R.id.ll_preview)
        iv_post = findViewById(R.id.iv_post)
        iv_delete = findViewById(R.id.iv_delete)
        iv_exit = findViewById(R.id.iv_exit)
        tv_link_domain = findViewById(R.id.tv_link_domain)
        bt_post = findViewById(R.id.bt_post)
        la_loading = findViewById(R.id.la_loading)
        tv_title = findViewById(R.id.tv_title)
        editText = findViewById(R.id.et_link)

        iv_exit.setOnClickListener {
            finish()
        }
        iv_delete.setOnClickListener {
            ll_preview.visibility = View.GONE
        }
        bt_post.setOnClickListener {
            backToFinish(link)
        }



        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if (containsLink(s.toString())) {
                    ll_preview.visibility = View.VISIBLE
                    iv_delete.visibility = View.VISIBLE
                }
                else{
                    ll_preview.visibility = View.GONE
                    iv_delete.visibility = View.GONE

                }

                if (s.toString() == "") bt_post.setBackgroundResource(R.drawable.view_border_rounded_post_default)
                else bt_post.setBackgroundResource(R.drawable.view_border_rounded_post)
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    private fun getElementsJsoup(url: String) {
        link = Link()
        Utils.getJsoupData(url)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result: Document ->
                val metaTags = result.getElementsByTag("meta")
                for (element in metaTags) {
                    when {
                        element.attr("property").equals("og:image") -> {
                            link.img = element.attr("content")
                            Picasso.get().load(element.attr("content")).into(iv_post)
                        }
                        element.attr("property").equals("og:description") -> {
                            link.domain = element.attr("content")
                            tv_link_domain.text = element.attr("content")
                        }
                        element.attr("property").equals("og:title") -> {
                            link.title = element.attr("content")
                            tv_title.text = element.attr("content")
                        }
                    }
                }
                la_loading.visibility = View.GONE
            }
    }

    private fun containsLink(input: String): Boolean {
        val parts = input.split(" ")
        for (item in parts) {
            if (Patterns.WEB_URL.matcher(item).matches()) {
                getElementsJsoup(item)
                isFindLink = true
                return true
            }
        }
        return false
    }

    fun backToFinish(link: Link) {
        val intent = Intent()
        intent.putExtra("link",link)
        setResult(RESULT_OK, intent)
        finish()
    }

}