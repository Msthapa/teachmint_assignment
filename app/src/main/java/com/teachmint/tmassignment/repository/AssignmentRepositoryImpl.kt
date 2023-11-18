package com.teachmint.tmassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.teachmint.tmassignment.data.local.LocalDataSource
import com.teachmint.tmassignment.data.model.RepositoryUiModel
import com.teachmint.tmassignment.data.remote.RemoteDataSource
import com.teachmint.tmassignment.data.remote.model.toRepositoryItemList
import com.teachmint.tmassignment.util.DataWrapper
import javax.inject.Inject

class AssignmentRepositoryImpl @Inject constructor(
    private val assignmentLocalDataSource: LocalDataSource,
    private val assignmentNetworkDataSource: RemoteDataSource
) : AssignmentRepository {
    override suspend fun searchRepositories(query: String, perPage : Int, pageNo : Int): LiveData<DataWrapper<List<RepositoryUiModel>>> {
        return liveData {
            emit(DataWrapper.loading())
            assignmentNetworkDataSource.searchRepositories(query, perPage, pageNo)?.let {
                if (it.status == DataWrapper.Status.SUCCESS) {
                    val uiModel = DataWrapper(DataWrapper.Status.SUCCESS,it.data?.toRepositoryItemList(), null, 200)
                    emit(uiModel)
                } else if (it.status == DataWrapper.Status.ERROR) {
                    emit(DataWrapper.error(it.statusCode, it.message ?: ""))
                }

            }
        }
    }


}