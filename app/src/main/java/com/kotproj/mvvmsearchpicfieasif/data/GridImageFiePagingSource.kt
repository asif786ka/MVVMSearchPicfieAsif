package com.kotproj.mvvmsearchpicfieasif.data

import androidx.paging.PagingSource
import com.kotproj.mvvmsearchpicfieasif.api.GridImageFieApi
import retrofit2.HttpException
import java.io.IOException

private const val GRIDIMAGE_STARTING_PAGE_INDEX = 1

/*
Below class helps for pagination and uses the jetpack pagination api
*/

class GridImageFiePagingSource(
    private val gridImageFieApi: GridImageFieApi,
    private val query: String
) : PagingSource<Int, GridImageFiePhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GridImageFiePhoto> {
        val position = params.key ?: GRIDIMAGE_STARTING_PAGE_INDEX

        return try {
            val response = gridImageFieApi.searchPhotos(query, position, params.loadSize)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == GRIDIMAGE_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}