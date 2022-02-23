package com.example.facebook.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.facebook.R
import com.example.facebook.helper.Utils
import com.example.facebook.model.Link
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jsoup.nodes.Document
import java.net.URI
import java.net.URL


class PostActivity : AppCompatActivity() {

    lateinit var editText:EditText
    lateinit var iv_post:ImageView
    lateinit var iv_delete:ImageView
    lateinit var tv_link_domain:TextView
    lateinit var tv_title:TextView
    lateinit var iv_exit:ImageView
    lateinit var ll_preview:LinearLayout
    lateinit var bt_post:Button
    private var isFindLink = false
    lateinit var link:Link
    lateinit var value:String

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

            override fun afterTextChanged(s: Editable) {}
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
                            val uri = URL(url)
                            link.domain = uri.host
                            tv_link_domain.text = uri.host
                        }
                        element.attr("property").equals("og:title") -> {
                            link.title = element.attr("content")
                            tv_title.text = element.attr("content")
                        }
                    }
                }
                value = editText.text.toString().trim()
            }
    }

    private fun containsLink(input: String): Boolean {
        val parts = input.split(" ")
        for (item in parts) {
            if (!isFindLink && Patterns.WEB_URL.matcher(item).matches()) {
                getElementsJsoup(item)
                isFindLink = true
                return true
            }
        }
        return false
    }

    fun backToFinish(link: Link) {
        link.fullname = "Richard Feynman"
        link.profile = R.drawable.im_person_00
        link.link = value
        val intent = Intent()
        intent.putExtra("link",link)
        setResult(RESULT_OK, intent)
        finish()
    }

}