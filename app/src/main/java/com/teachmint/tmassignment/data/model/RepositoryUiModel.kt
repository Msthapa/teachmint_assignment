package com.teachmint.tmassignment.data.model

import com.teachmint.tmassignment.data.local.room.RepositoryEntity

data class RepositoryUiModel (
    val repoId : Int,
    val repoName : String,
    val repoFullName : String,
    val ownerName : String,
    val description : String?,
    val ownerImageUrl : String,
    val contributors_url : String,
    val project_link : String,
    )

fun RepositoryUiModel.toEntity() = RepositoryEntity(
repoId = this@toEntity.repoId,
repoName = this@toEntity.repoName,
repoFullName = this@toEntity.repoFullName,
ownerName = this@toEntity.ownerName,
description = this@toEntity.description,
ownerImageUrl = this@toEntity.ownerImageUrl,
contributors_url = this@toEntity.contributors_url,
project_link = this@toEntity.project_link
)