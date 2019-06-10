package com.example.androidpaginglibrary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * This class handle the adapter for recyclerView
 */
class GitRepoAdapter : PagedListAdapter<GitRepo, GitRepoAdapter.RepoViewHolder>(REPO_COMPARATOR) {

    /**
     * we need to create a data source for fetching the page list while using the paging library
     * this data source is the base class for loading pages of snapshot data into the page list
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoAdapter.RepoViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: GitRepoAdapter.RepoViewHolder, position: Int) {
        val repo: GitRepo? = getItem(position)
        repo?.let {
            holder.setData(repo)
        }
    }

    class RepoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun setData(gitRepo: GitRepo) {
            view.repo_name.text = gitRepo.fullName
            view.repo_description.text = gitRepo.description
            view.repo_language.text = gitRepo.languages
            view.repo_stars.text = gitRepo.stars.toString()
            view.repo_forks.text = gitRepo.forks.toString()
        }
    }

    companion object {
        private val REPO_COMPARATOR: DiffUtil.ItemCallback<GitRepo> = object : DiffUtil.ItemCallback<GitRepo>() {
            override fun areItemsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean =
                oldItem.fullName == newItem.fullName

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean =
                oldItem == newItem

        }
    }
}