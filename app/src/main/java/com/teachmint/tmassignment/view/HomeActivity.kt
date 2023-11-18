package com.teachmint.tmassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.teachmint.tmassignment.R
import com.teachmint.tmassignment.databinding.ActivityHomeBinding
import com.teachmint.tmassignment.util.DataWrapper
import com.teachmint.tmassignment.viewmodel.AssignmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityHomeBinding
    private lateinit var navController: NavController
    private val mViewModel : AssignmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
       val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_home_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun searchGitRepos(query : String) {
        lifecycleScope.launch(Dispatchers.Main) {
            mViewModel.searchRepositories("hello", 10, 1).observe(this@HomeActivity,
                Observer {
                    when (it.status) {
                        DataWrapper.Status.LOADING -> {
                            Log.d("MsThapa","network call happening")
                        }
                        DataWrapper.Status.SUCCESS -> {
                            Log.d("MsThapa","network call success")

                        }
                        DataWrapper.Status.ERROR -> {
                            Log.d("MsThapa","network call failure")
                        }
                    }
                })
        }
    }
}