package com.kotlin.mvvm_retrofit_rx

import io.reactivex.Observable
import retrofit2.http.GET

interface JsonPlaceholderApi {
    @get:GET("/posts")
    val posts: Observable<List<Post>>
}