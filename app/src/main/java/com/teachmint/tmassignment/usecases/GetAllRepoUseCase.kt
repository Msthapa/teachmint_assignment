package com.teachmint.tmassignment.usecases

import com.teachmint.tmassignment.data.model.RepositoryUiModel
import com.teachmint.tmassignment.repository.AssignmentRepository
import javax.inject.Inject

class GetAllRepoUseCase @Inject constructor(private val repository : AssignmentRepository) {

     suspend fun getAllRepositories(): List<RepositoryUiModel?>? {
        return repository.getAllRepositories()
    }
}