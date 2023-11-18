package com.teachmint.tmassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teachmint.tmassignment.data.model.ContributorListUiModel
import com.teachmint.tmassignment.data.model.RepositoryUiModel
import com.teachmint.tmassignment.data.remote.model.toContributorListUiModel
import com.teachmint.tmassignment.data.remote.model.toRepositoryItemList
import com.teachmint.tmassignment.usecases.GetContributorsUseCase
import com.teachmint.tmassignment.usecases.GetRepositoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssignmentViewModel @Inject constructor(
    private val repositoryListUseCase: GetRepositoryListUseCase,
    private val contributorListUseCase : GetContributorsUseCase
) : ViewModel() {

    private var searchRepoJob: Job? = null
    var currentlySelectedRepo : RepositoryUiModel ?= null
    val repoList = MutableLiveData<List<RepositoryUiModel>>()
    val contributorList = MutableLiveData<List<ContributorListUiModel>>()

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

    fun getContributorList(url : String) {
         viewModelScope.launch {
             val response = contributorListUseCase.getContributorsList(url)
             if(response.isSuccessful){
                 contributorList.postValue(response.body()?.toContributorListUiModel())
             }else {
                 contributorList.postValue(listOf())
             }
         }
    }
}
