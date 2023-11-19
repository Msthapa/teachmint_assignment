package com.teachmint.tmassignment.repository


import com.teachmint.tmassignment.data.local.LocalDataSource
import com.teachmint.tmassignment.data.model.RepositoryUiModel
import com.teachmint.tmassignment.data.remote.RemoteDataSource
import javax.inject.Inject

class AssignmentRepositoryImpl @Inject constructor(
    private val assignmentLocalDataSource: LocalDataSource,
    private val assignmentNetworkDataSource: RemoteDataSource
) : AssignmentRepository {
    override suspend fun searchRepositories(query: String, perPage : Int, pageNo : Int) =
        assignmentNetworkDataSource.searchRepositories(query, perPage, pageNo)

    override suspend fun getContributorsList(url: String) =
        assignmentNetworkDataSource.getContributorsList(url)


    override suspend fun insertRepositoryIntoDataBase(repositoryUIModel: RepositoryUiModel) {
        assignmentLocalDataSource.insertRepositoryIntoDataBase(repositoryUIModel)
    }

    override suspend fun getAllRepositories(): List<RepositoryUiModel?>? {
        return assignmentLocalDataSource.getAllRepositories()
    }

}