package com.teachmint.tmassignment.data.local

import com.teachmint.tmassignment.data.local.room.RepositoryDAO
import com.teachmint.tmassignment.data.local.room.toRepositoryUiModel
import com.teachmint.tmassignment.data.model.RepositoryUiModel
import com.teachmint.tmassignment.data.model.toEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val repositoryDAO : RepositoryDAO) {

    suspend fun insertRepositoryIntoDataBase(repositoryUIModel : RepositoryUiModel){
         repositoryDAO.insertRepositoryItem(repositoryUIModel.toEntity())
    }

    suspend fun getAllRepositories() : List<RepositoryUiModel?>?{
        return repositoryDAO.getAllRepositories()?.map { it?.toRepositoryUiModel() }
    }
}