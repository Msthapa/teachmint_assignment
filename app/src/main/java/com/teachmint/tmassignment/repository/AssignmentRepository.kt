package com.teachmint.tmassignment.repository

import androidx.lifecycle.LiveData
import com.teachmint.tmassignment.data.model.RepositoryUiModel
import com.teachmint.tmassignment.util.DataWrapper

interface AssignmentRepository {
    suspend fun searchRepositories(query : String, perPage : Int, pageNo : Int) : LiveData<DataWrapper<List<RepositoryUiModel>>>

}