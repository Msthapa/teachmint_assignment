package com.teachmint.tmassignment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teachmint.tmassignment.databinding.FragmentRepoDetailBinding


class RepoDetailFragment : Fragment() {

    private lateinit var mBinding : FragmentRepoDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentRepoDetailBinding.inflate(inflater, container, false)
        return mBinding.root
    }

}