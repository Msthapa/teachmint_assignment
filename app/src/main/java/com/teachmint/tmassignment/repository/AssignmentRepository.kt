package com.teachmint.tmassignment.repository

import com.teachmint.tmassignment.data.remote.model.RepoResponse
import retrofit2.Response

interface AssignmentRepository {
    suspend fun searchRepositories(query : String, perPage : Int, pageNo : Int) : Response<RepoResponse>

}