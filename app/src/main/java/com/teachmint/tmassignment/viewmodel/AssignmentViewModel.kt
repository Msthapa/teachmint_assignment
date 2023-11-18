package com.teachmint.tmassignment.viewmodel

import androidx.lifecycle.ViewModel
import com.teachmint.tmassignment.usecases.GetRepositoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AssignmentViewModel @Inject constructor(
    private val repositoryListUseCase: GetRepositoryListUseCase
) : ViewModel() {

   suspend fun searchRepositories(query : String, perPage : Int, pageNo : Int) = repositoryListUseCase.searchRepositories(query, perPage, pageNo)
}