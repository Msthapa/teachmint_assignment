package com.teachmint.tmassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teachmint.tmassignment.data.model.RepositoryUiModel
import com.teachmint.tmassignment.data.remote.model.toRepositoryItemList
import com.teachmint.tmassignment.usecases.GetRepositoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssignmentViewModel @Inject constructor(
    private val repositoryListUseCase: GetRepositoryListUseCase
) : ViewModel() {

    private var searchRepoJob: Job? = null
    val repoList = MutableLiveData<List<RepositoryUiModel>>()
    var currentlySelectedRepo : RepositoryUiModel ?= null

    fun searchRepositories(query: String, perPage: Int, pageNo: Int) {
        searchRepoJob?.cancel()
        searchRepoJob = viewModelScope.launch {
            val response = repositoryListUseCase.searchRepositories(query, perPage, pageNo)
            if (response.isSuccessful) {
                repoList.postValue(response.body()?.toRepositoryItemList())

            } else {
                repoList.postValue(listOf())
            }
        }
    }
}
