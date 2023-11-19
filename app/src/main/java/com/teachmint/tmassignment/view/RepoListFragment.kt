package com.teachmint.tmassignment.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.teachmint.tmassignment.R
import com.teachmint.tmassignment.adapters.RepoListAdapter
import com.teachmint.tmassignment.databinding.FragmentRepoListBinding
import com.teachmint.tmassignment.util.AppConstants.isNetworkAvailable
import com.teachmint.tmassignment.util.BaseFragment
import com.teachmint.tmassignment.viewmodel.AssignmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoListFragment : BaseFragment() {

    private lateinit var mBinding : FragmentRepoListBinding
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
        registerNetworkCallback()
        observeData()
        return mBinding.root
    }

    private fun implementSearchRepo() {
        repoListAdapter = RepoListAdapter(){ clickedRepoItem ->
           mViewModel.currentlySelectedRepo = clickedRepoItem
            findNavController().navigate(R.id.action_repolist_to_repodetail)
        }
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
                context?.let {
                    if(isNetworkAvailable(it)) {
                        newText?.let {
                            if (it.isNotEmpty()) {
                                searchGitRepos(it)
                            } else {
                                // need to show local list
                            }
                        } ?: kotlin.run {
                            // need to show local list
                        }
                    }else{
                        mViewModel.updateListOnNoNetwork()
                    }
                }
                return true
            }

        })
    }
    private fun searchGitRepos(query: String) {
        mViewModel.searchRepositories(query, 10, 1)
    }

    private fun registerNetworkCallback() {
        val connectivityManager: ConnectivityManager? =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkRequest: NetworkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager?.registerNetworkCallback(networkRequest, networkCallback)
    }

    private val networkCallback: ConnectivityManager.NetworkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            Toast.makeText(context, "Internet connection came back", Toast.LENGTH_SHORT).show()
        }

        override fun onLost(network: Network) {
           Toast.makeText(context,"Intenet connection gone!",Toast.LENGTH_SHORT).show()
            mViewModel.updateListOnNoNetwork()
        }
    }

    private fun observeData() {
        mViewModel.repoList.observe(viewLifecycleOwner,
            Observer {
                repoListAdapter?.resetRepoList(ArrayList(it))
            })
    }
}