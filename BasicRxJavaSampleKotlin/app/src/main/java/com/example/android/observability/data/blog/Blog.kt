package com.example.android.observability.data.blog

import com.google.gson.annotations.SerializedName

data class Blog(
        @SerializedName("pk")
        var id: Int,
        var title: String,
        var body: String,
        var image: String,
        var category: String
)