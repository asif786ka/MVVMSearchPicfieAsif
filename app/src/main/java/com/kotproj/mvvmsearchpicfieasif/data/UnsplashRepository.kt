package com.kotproj.mvvmsearchpicfieasif.data

import com.kotproj.mvvmsearchpicfieasif.api.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepository @Inject constructor(private val unsplashApi: UnsplashApi) {
}