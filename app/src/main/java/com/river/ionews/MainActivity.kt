package com.river.ionews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    private val url: String = "https://newsapi.org/v2/everything?q=kotlin&from=2019-11-01&sortBy=publishedAt&apiKey=acaab48413c4495c98430b264ad7d7cb"
    private val news = ArrayList<News>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set recyclerwiew layout
        recyclerView = findViewById(R.id.my_recycler_view)

        // Get data from endpoint
        getData()
    }

    /**
     * Get data from endpoint and set data to recyclerview
     */
    fun getData() {
        // Volley queue
        var queue = Volley.newRequestQueue(this)

        // Set Volley Json Object with GET request
        val newsJsonObjectRequest = JsonObjectRequest(url, null,
            Listener { response ->
                // On OK response
                try {
                    // Try parse the Json Array
                    var newsJsonArray: JSONArray = response.getJSONArray("articles")

                    // Loop for parse Json Array to news Array List
                    for (i in 0 until newsJsonArray.length()) {
                        // Get item from json Array
                        var item = newsJsonArray.getJSONObject(i)

                        // Get values from json Array
                        var title = item.getString("title")
                        var excerpt = item.getString("description")
                        var image = R.drawable.news_1

                        // Set values from news Array List
                        news.add(
                            News(
                                title,
                                excerpt,
                                image
                            )
                        )
                    }

                    // Call to setup recycler view with data
                    setupRecycler()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            // On error response
            Response.ErrorListener { error -> error.printStackTrace() })

        // Add object request to Volley queue
        queue.add(newsJsonObjectRequest)
    }

    /**
     * Setup data for Recyclerview
     */
    private fun setupRecycler() {
        // Set layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        // Set Adapter with news array list
        val adapter = AdapterNews(news)

        // Set adapter to recycler
        recyclerView.adapter = adapter
    }
}
