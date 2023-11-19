package com.teachmint.tmassignment.repository

import com.teachmint.tmassignment.data.model.RepositoryUiModel
import com.teachmint.tmassignment.data.remote.model.ContributorListResponse
import com.teachmint.tmassignment.data.remote.model.RepoResponse
import retrofit2.Response

interface AssignmentRepository {
    suspend fun searchRepositories(query : String, perPage : Int, pageNo : Int) : Response<RepoResponse>

    suspend fun getContributorsList(url : String) : Response<ContributorListResponse>

    suspend fun insertRepositoryIntoDataBase(repositoryUIModel : RepositoryUiModel)

    suspend fun getAllRepositories() : List<RepositoryUiModel?>?

}