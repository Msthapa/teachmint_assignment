package com.teachmint.tmassignment.usecases

import com.teachmint.tmassignment.repository.AssignmentRepository
import javax.inject.Inject

class GetRepositoryListUseCase @Inject constructor(private val repository : AssignmentRepository) {

}