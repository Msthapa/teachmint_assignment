package com.teachmint.tmassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.teachmint.tmassignment.R
import com.teachmint.tmassignment.databinding.ActivityHomeBinding
import com.teachmint.tmassignment.viewmodel.AssignmentViewModel
import dagger.hilt.android.AndroidEntryPoint

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

    override fun onStart() {
        super.onStart()
        mViewModel.updateRemainingRepoNo()
    }


}