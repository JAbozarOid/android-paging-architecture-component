package com.example.androidpaginglibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // *** >>> "start of this line for testing connect to api server and fetching data" <<< ***
        // *** we want to fetch data in the MainActivity
        // *** we should instantiated Services and call api
//  1-      val service: GitRepoService = GitRepoServiceBuilder.buildService(GitRepoService::class.java)

        // *** we should pass data parameters as a query string
//  2-      val call: Call<GitRepoResponse> = service.getRepositories("tetris+language:assembly","stars","desc")

        // *** call the api async
        /* 3- start
        call.enqueue(object : Callback<GitRepoResponse> {
            override fun onFailure(call: Call<GitRepoResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<GitRepoResponse>, response: Response<GitRepoResponse>) {
                if (response.isSuccessful) {
                    val apiResponse: GitRepoResponse = response.body()!!
                    val responseItems: List<GitRepo>? = apiResponse.items

                    val size: String? = responseItems?.let {
                        responseItems.size.toString()
                    }

                    Toast.makeText(this@MainActivity, size, Toast.LENGTH_LONG).show()
                }
            }

        })
        */
        // *** >>> "end of this line for testing connect to api server and fetching data" <<< *** -> commented lines with "numbers'


        /**
         * set recycler view for display data and initialize viewModel for MainActivity
         */
        val adapter = GitRepoAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemViewModel: GitRepoViewModel = ViewModelProviders.of(this).get(GitRepoViewModel::class.java)

        itemViewModel.gitRepoPagedList.observe(this, Observer {
            adapter.submitList(it)
        })
        recyclerView.adapter = adapter
    }
}
