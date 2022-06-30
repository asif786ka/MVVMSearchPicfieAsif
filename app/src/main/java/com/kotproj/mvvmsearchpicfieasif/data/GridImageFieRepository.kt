package com.kotproj.mvvmsearchpicfieasif.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.kotproj.mvvmsearchpicfieasif.api.GridImageFieApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GridImageFieRepository @Inject constructor(private val gridImageFieApi: GridImageFieApi) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GridImageFiePagingSource(gridImageFieApi, query) }
        ).liveData
}