package com.teachmint.tmassignment.usecases

import com.teachmint.tmassignment.repository.AssignmentRepository
import javax.inject.Inject

class GetContributorsUseCase @Inject constructor(private val repository : AssignmentRepository)  {

    suspend fun getContributorsList(url : String) = repository.getContributorsList(url)
}