package com.example.androidpaginglibrary

import androidx.paging.PageKeyedDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * data source queried to load pages of content into the page list
 * there are there kind of dataSources
 * 1- PageKeyedDataSource : use if we are loading the contents in form of pages -> the key and value
 * 2- ItemKeyDataSource : if you have  a specific numbers of items
 * 3- PositionalDataSource :
 */
class GitRepoDataSource : PageKeyedDataSource<Int, GitRepo>() {

    /**
     * this function first executed
     */
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GitRepo>) {

        val service: GitRepoService = GitRepoServiceBuilder.buildService(GitRepoService::class.java)
        val call: Call<GitRepoResponse> = service.getRepositories(PAGE_TOPIC,PAGE_SORT,PAGE_ORDER)

        // *** async call
        call.enqueue(object : Callback<GitRepoResponse>{
            override fun onFailure(call: Call<GitRepoResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<GitRepoResponse>, response: Response<GitRepoResponse>) {
                if (response.isSuccessful){
                    val apiResponse:GitRepoResponse = response.body()!!
                    val responseItems: List<GitRepo>? = apiResponse.items
                    responseItems?.let {
                        callback.onResult(responseItems,null, 10 + 1)
                    }
                }
            }

        })
    }

    /**
     * when we scroll down for load more data
     */
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GitRepo>) {

        val service: GitRepoService = GitRepoServiceBuilder.buildService(GitRepoService::class.java)
        val call: Call<GitRepoResponse> = service.getRepositories(PAGE_TOPIC,PAGE_SORT,PAGE_ORDER)

        // *** async call
        call.enqueue(object : Callback<GitRepoResponse>{
            override fun onFailure(call: Call<GitRepoResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<GitRepoResponse>, response: Response<GitRepoResponse>) {
                if (response.isSuccessful){
                    val apiResponse:GitRepoResponse = response.body()!!
                    val responseItems: List<GitRepo>? = apiResponse.items

                    val key:Int = if (apiResponse.totalCount > params.key) params.key + 1 else apiResponse.totalCount

                    responseItems?.let {
                        callback.onResult(responseItems,key)
                    }
                }
            }

        })
    }

    /**
     * when we are scroll back for getting previous data
     */
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GitRepo>) {
        val service: GitRepoService = GitRepoServiceBuilder.buildService(GitRepoService::class.java)
        val call: Call<GitRepoResponse> = service.getRepositories(params.key.toString(),PAGE_SORT,PAGE_ORDER)

        // *** async call
        call.enqueue(object : Callback<GitRepoResponse>{
            override fun onFailure(call: Call<GitRepoResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<GitRepoResponse>, response: Response<GitRepoResponse>) {
                if (response.isSuccessful){
                    val apiResponse:GitRepoResponse = response.body()!!
                    val responseItems: List<GitRepo>? = apiResponse.items

                    val key:Int = if (params.key > 1) params.key - 1 else 0

                    responseItems?.let {
                        callback.onResult(responseItems,key)
                    }
                }
            }

        })
    }

    companion object {
        const val PAGE_TOPIC = "tetris+language:assembly"
        const val PAGE_SORT = "stars"
        const val PAGE_ORDER = "desc"

    }
}