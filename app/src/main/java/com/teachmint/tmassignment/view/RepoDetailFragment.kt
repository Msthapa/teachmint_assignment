package com.teachmint.tmassignment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.teachmint.tmassignment.R
import com.teachmint.tmassignment.adapters.ContributorsListAdapter
import com.teachmint.tmassignment.databinding.FragmentRepoDetailBinding
import com.teachmint.tmassignment.util.AppConstants
import com.teachmint.tmassignment.viewmodel.AssignmentViewModel


class RepoDetailFragment : Fragment() {

    private lateinit var mBinding : FragmentRepoDetailBinding
    private val mViewModel : AssignmentViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentRepoDetailBinding.inflate(inflater, container, false)
        inflateUI()
        addClickListeners()
        return mBinding.root
    }

    private fun inflateUI() {
        mViewModel.currentlySelectedRepo?.let { repoItem ->
            mBinding.tvRepoName.text = getString(R.string.txt_repo_full_name, repoItem.repoFullName)
            mBinding.tvOwnerName.text = getString(R.string.txt_owner_name, repoItem.ownerName)
            context?.let {
                Glide.with(it).load(repoItem.ownerImageUrl).apply(
                    RequestOptions().placeholder(R.mipmap.ic_launcher)
                ).into(mBinding.ivOwnerPic)
            }
            mBinding.tvDescription.text = repoItem.description
            mBinding.tvProjectLink.text = repoItem.project_link
            mViewModel.getContributorList(repoItem.contributors_url)
            observeContributorList()
        }
    }

    private fun observeContributorList() {
        mViewModel.contributorList.observe(viewLifecycleOwner, Observer {
            val mAdapter = ContributorsListAdapter(it)
            mBinding.rvContributors.adapter = mAdapter
          mBinding.rvContributors.layoutManager = GridLayoutManager(context, 3)
            mBinding.contributors.text = getString(R.string.txt_Contributors, it.size.toString())
        })
    }

    private fun addClickListeners() {
        mBinding?.tvProjectLink?.setOnClickListener {
            findNavController().navigate(
                R.id.action_repodetail_to_projectwebview,
                bundleOf(AppConstants.PROJECT_LINK to mViewModel.currentlySelectedRepo?.project_link)
            )
        }
    }

}