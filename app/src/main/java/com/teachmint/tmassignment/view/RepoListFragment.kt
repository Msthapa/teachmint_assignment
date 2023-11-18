package com.teachmint.tmassignment.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.teachmint.tmassignment.adapters.RepoListAdapter
import com.teachmint.tmassignment.databinding.FragmentRepoListBinding
import com.teachmint.tmassignment.util.BaseFragment
import com.teachmint.tmassignment.viewmodel.AssignmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class RepoListFragment : BaseFragment() {

    lateinit var mBinding : FragmentRepoListBinding
    private val mViewModel : AssignmentViewModel by activityViewModels()
    private var repoListAdapter : RepoListAdapter ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentRepoListBinding.inflate(layoutInflater, container, false)
        implementSearchRepo()
        return mBinding.root
    }

    private fun implementSearchRepo() {
        repoListAdapter = RepoListAdapter()
        mBinding.rvRepoList.adapter = repoListAdapter
        mBinding.rvRepoList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        addQueryChangeListener()
    }
    private fun addQueryChangeListener() {
        mBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isNotEmpty()) {
                        searchGitRepos(it)
                    } else {
                        // need to show local list
                    }
                } ?: kotlin.run {
                    // need to show local list
                }
                return true
            }

        })
    }
    private fun searchGitRepos(query: String) {
        mViewModel.searchRepositories(query, 10, 1)
        mViewModel.repoList.observe(viewLifecycleOwner,
            Observer {
                Log.d("Mstahpa", "repoList observed ${it.size}")
                repoListAdapter?.resetRepoList(ArrayList(it))
            })
    }

}