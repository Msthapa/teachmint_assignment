package com.teachmint.tmassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teachmint.tmassignment.data.model.ContributorUiModel
import com.teachmint.tmassignment.data.model.RepositoryUiModel
import com.teachmint.tmassignment.data.remote.model.toContributorListUiModel
import com.teachmint.tmassignment.data.remote.model.toRepositoryItemList
import com.teachmint.tmassignment.usecases.GetAllRepoUseCase
import com.teachmint.tmassignment.usecases.GetContributorsUseCase
import com.teachmint.tmassignment.usecases.GetRepositoryListUseCase
import com.teachmint.tmassignment.usecases.InsertRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AssignmentViewModel @Inject constructor(
    private val repositoryListUseCase: GetRepositoryListUseCase,
    private val contributorListUseCase : GetContributorsUseCase,
    private val insertRepoUseCase: InsertRepoUseCase,
    private val getAllRepoUseCase: GetAllRepoUseCase
) : ViewModel() {

    private var searchRepoJob: Job? = null
    var currentlySelectedRepo : RepositoryUiModel ?= null
    val repoList = MutableLiveData<List<RepositoryUiModel>>()
    val contributorList = MutableLiveData<List<ContributorUiModel>>()
    var remainingOfflineRepo = 15

    fun searchRepositories(query: String, perPage: Int, pageNo: Int, byScroll : Boolean,byScrollEnd : (item: List<RepositoryUiModel>) -> Unit) {
        searchRepoJob?.cancel()
        try {
            searchRepoJob = viewModelScope.launch {
                val response = repositoryListUseCase.searchRepositories(query, perPage, pageNo)
                if (response.isSuccessful) {
                    val repositoryUiItem = response.body()?.toRepositoryItemList() ?: listOf()
                    repositoryUiItem.forEach {
                        if (remainingOfflineRepo > 0) {
                            remainingOfflineRepo -= 1
                            insertRepoUseCase.insertRepositoryIntoDataBase(it)
                        }
                    }
                    if(!byScroll) {
                        repoList.postValue(repositoryUiItem)
                    }else {
                        byScrollEnd(repositoryUiItem)
                    }
                } else {
                    if(!byScroll)
                    repoList.postValue(listOf())
                }
            }
        }catch (e : Exception) {
            e.printStackTrace()
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

    fun updateRemainingRepoNo(){
        viewModelScope.launch {
            getAllRepoUseCase.getAllRepositories()?.let {
                remainingOfflineRepo -= it.size
            }
        }
    }

    fun updateListOnNoNetwork(){
        viewModelScope.launch {
            getAllRepoUseCase.getAllRepositories()?.let {
                repoList.postValue(it.filterNotNull())
            }
        }
    }
}
