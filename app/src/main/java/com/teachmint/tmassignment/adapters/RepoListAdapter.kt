package com.teachmint.tmassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teachmint.tmassignment.R
import com.teachmint.tmassignment.data.model.RepositoryUiModel
import com.teachmint.tmassignment.databinding.LayoutRepositoryItemBinding

class RepoListAdapter : RecyclerView.Adapter<RepoListAdapter.RepoListItemViewHolder>() {

    private var repoList = ArrayList<RepositoryUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListItemViewHolder {
        val binding = LayoutRepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoListItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoListItemViewHolder, position: Int) {
       holder.bind(repoList[position])
    }

    inner class RepoListItemViewHolder(private val  binding : LayoutRepositoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : RepositoryUiModel) {
            with(binding.root.context){
                binding.tvRepoName.text = getString(R.string.txt_repo_name, item.repoName)
                binding.tvOwnerName.text = getString(R.string.txt_owner_name, item.ownerName)
            }

        }
    }

    fun addRepositoryList(repoList: List<RepositoryUiModel>) {
        this.repoList.addAll(repoList)
        notifyDataSetChanged()
    }

    fun resetRepoList(repoList: ArrayList<RepositoryUiModel>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }
}