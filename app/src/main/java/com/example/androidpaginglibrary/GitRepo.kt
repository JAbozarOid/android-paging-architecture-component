package com.example.androidpaginglibrary

import com.google.gson.annotations.SerializedName

/**
 * this class handle the response model of a json api
 */
class GitRepo {

    @field:SerializedName("full_name")
    var fullName : String? = null

    val description : String? = null

    @field:SerializedName("stargazers_count")
    val stars : Int = 0

    @field:SerializedName("forks_count")
    val forks : Int = 0

    @field:SerializedName("language")
    val languages : String? = null

}

class GitRepoResponse {
    @field:SerializedName("total_count")
    var totalCount: Int = 0
    var items: List<GitRepo>? = null
}