package com.teachmint.tmassignment.usecases

import com.teachmint.tmassignment.data.model.RepositoryUiModel
import com.teachmint.tmassignment.repository.AssignmentRepository
import javax.inject.Inject

class InsertRepoUseCase @Inject constructor(private val repository : AssignmentRepository) {

     suspend fun insertRepositoryIntoDataBase(repositoryUIModel: RepositoryUiModel) {
         repository.insertRepositoryIntoDataBase(repositoryUIModel)
    }
}