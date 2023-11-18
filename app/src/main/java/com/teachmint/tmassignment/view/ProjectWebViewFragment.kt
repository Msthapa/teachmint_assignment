package com.teachmint.tmassignment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teachmint.tmassignment.databinding.FragmentProjectWebViewBinding


class ProjectWebViewFragment : Fragment() {

    lateinit var mBinding : FragmentProjectWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentProjectWebViewBinding.inflate(layoutInflater, container, false)
        inflateUI()
        return mBinding.root
    }

    private fun inflateUI() {

    }



}