package com.teachmint.tmassignment.data.remote.model

import com.teachmint.tmassignment.data.model.ContributorListUiModel


class ContributorListResponse : ArrayList<ContributorListResponseItem>()


fun ContributorListResponse.toContributorListUiModel() : List<ContributorListUiModel> {
    val contributorList = mutableListOf<ContributorListUiModel>()
    this.forEach {
        contributorList.add(
            ContributorListUiModel(
                login = it.login,
                avatarUrl = it.avatarUrl
            )
        )
    }
    return contributorList
}