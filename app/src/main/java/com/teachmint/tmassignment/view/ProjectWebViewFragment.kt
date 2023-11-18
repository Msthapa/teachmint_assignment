package com.teachmint.tmassignment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.teachmint.tmassignment.databinding.FragmentProjectWebViewBinding
import com.teachmint.tmassignment.util.AppConstants


class ProjectWebViewFragment : Fragment() {

    lateinit var mBinding : FragmentProjectWebViewBinding
    private var webUrl : String ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentProjectWebViewBinding.inflate(layoutInflater, container, false)
        arguments?.let {
            webUrl = it.getString(AppConstants.PROJECT_LINK)
        }
        inflateUI()
        return mBinding.root
    }

    private fun inflateUI() {
        webUrl?.let {
            mBinding.wvProject.loadUrl(it)
            mBinding.wvProject.webViewClient = object :  WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    mBinding.pbWeb.visibility = View.GONE
                }
            }

        } ?: kotlin.run {
            Toast.makeText(context, "render issue!", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }



}