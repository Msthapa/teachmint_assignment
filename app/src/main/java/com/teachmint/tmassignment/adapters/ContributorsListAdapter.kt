package com.teachmint.tmassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.teachmint.tmassignment.R
import com.teachmint.tmassignment.data.model.ContributorUiModel
import com.teachmint.tmassignment.databinding.LayoutContributorBinding

class ContributorsListAdapter(private val contributorList : List<ContributorUiModel>) : RecyclerView.Adapter<ContributorsListAdapter.ContributorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val binding = LayoutContributorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContributorViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contributorList.size
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        holder.bind(contributorList[position])
    }

    inner class ContributorViewHolder(private val  binding : LayoutContributorBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : ContributorUiModel) {
            with(binding.root.context){
                binding.tvContributor.text = item.login

                Glide.with(binding.root.context).load(item.avatarUrl).apply(
                    RequestOptions().placeholder(R.mipmap.ic_launcher)
                ).into(binding.ivContributorPic)
            }
        }
    }
}