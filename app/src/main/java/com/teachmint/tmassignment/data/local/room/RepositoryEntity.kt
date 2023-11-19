package com.teachmint.tmassignment.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teachmint.tmassignment.data.model.RepositoryUiModel

@Entity(tableName = "repository_table")
data class RepositoryEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val repoId : Int,
    val repoName : String,
    val repoFullName : String,
    val ownerName : String,
    val description : String?,
    val ownerImageUrl : String,
    val contributors_url : String,
    val project_link : String,
)

fun RepositoryEntity.toRepositoryUiModel() = RepositoryUiModel(
    repoId = this@toRepositoryUiModel.repoId,
    repoName = this@toRepositoryUiModel.repoName,
    repoFullName = this@toRepositoryUiModel.repoFullName,
    ownerName = this@toRepositoryUiModel.ownerName,
    description = this@toRepositoryUiModel.description,
    ownerImageUrl = this@toRepositoryUiModel.ownerImageUrl,
    contributors_url = this@toRepositoryUiModel.contributors_url,
    project_link = this@toRepositoryUiModel.project_link,
)