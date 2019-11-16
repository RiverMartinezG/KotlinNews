package com.river.ionews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.my_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val news = ArrayList<News>()

        var queue = Volley.newRequestQueue(this)
        var url: String = "https://newsapi.org/v2/everything?q=kotlin&from=2019-11-01&sortBy=publishedAt&apiKey=acaab48413c4495c98430b264ad7d7cb"
        var myNews: JSONArray;

        val jsonObjectRequest = JsonObjectRequest(url,
            null,
            Response.Listener { response ->
                Log.i("PRO", "Response is: ${response}")
                myNews = response.getJSONArray("articles")
                Log.i("PRO", "Articles: ${myNews}")
            },
            Response.ErrorListener { error -> error.printStackTrace() })

        queue.add(jsonObjectRequest)

        news.add(
            News(
                "Android now supports the Kotlin programming language",
                "At its I/O 2017 developers conference today, Google announced Android is gaining official support for the Kotlin programming language, in addition to Java and C++. The news received the biggest applause from the audience so far. You can download the Kotlin plugin today for Android Studio.",
                R.drawable.news_1
            )
        )

        news.add(
            News(
                "Kotlin 1.0 Released: Pragmatic Language for JVM and Android",
                "It’s been a long and exciting road but we’ve finally reached the first big 1.0, and we’re celebrating the release by also presenting you with the new logo",
                R.drawable.news_2
                )
        )

        news.add(
            News(
                "The Language that Stole Android Developers’ Hearts",
                "In the early 1700s, Peter the Great, the czar of Russia, was busy nabbing land from his western neighbor, the Swedish Empire.",
                R.drawable.news_3
                )
        )

        news.add(
            News(
                "Building a fully featured burner phone with Kotlin",
                "You walk into your favourite coffee shop and today they ask you if you’d like to register for a loyalty card. All they need is your name and phone number.",
                R.drawable.news_4
                )
        )

        news.add(
            News(
                "Javalin 1.0.0 released",
                "Javalin is a very lightweight web framework for Kotlin and Java, inspired by Sparkjava and koa.js.",
                R.drawable.news_5
                )
        )

        val adapter = AdapterNews(news)

        recyclerView.adapter = adapter
    }
}
