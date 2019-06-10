package com.example.androidpaginglibrary

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface GitRepoService {

    @GET("search/repositories")
    fun getRepositories(
//        @Query("page") page: Int,
//        @Query("per_page") size: Int,
        @Query("q") q: String,
        @Query("sort") sort: String,
        @Query("order") order: String

    ): Call<GitRepoResponse>
}