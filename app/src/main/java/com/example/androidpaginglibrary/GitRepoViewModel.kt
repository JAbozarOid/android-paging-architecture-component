package com.example.androidpaginglibrary

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class GitRepoViewModel : ViewModel() {

    /**
     * fetching the page list data from the data source
     */
    var gitRepoPagedList: LiveData<PagedList<GitRepo>>
    private var liveDataSource: LiveData<GitRepoDataSource>

    init {
        val itemDataSourceFactory = GitRepoDataSourceFactory()
        liveDataSource = itemDataSourceFactory.gitRepoLiveDataSource

        val config: PagedList.Config = PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(10).build()

        gitRepoPagedList = LivePagedListBuilder(itemDataSourceFactory,config).build()
    }
}