package com.teachmint.tmassignment.usecases

import com.teachmint.tmassignment.repository.AssignmentRepository
import javax.inject.Inject

class GetRepositoryListUseCase @Inject constructor(private val repository : AssignmentRepository) {
    suspend fun searchRepositories(query : String, perPage : Int, pageNo : Int) = repository.searchRepositories(query, perPage, pageNo)
}