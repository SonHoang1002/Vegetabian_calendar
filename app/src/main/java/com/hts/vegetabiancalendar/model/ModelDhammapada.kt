package com.hts.vegetabiancalendar.model

import com.google.gson.annotations.SerializedName

data class ModelDhammapada (
    @SerializedName("title")
    val title: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("documents")
    val chapter: List<ModelChapterDhammapada>
)

data class ModelChapterDhammapada(
    @SerializedName("chapter_number")
    val chapterNumber : Int,
    @SerializedName("chapter_title")
    val chapterTitle : String,
    @SerializedName("verses")
    val verses: List<ModelVerseDhammapada>,
    @SerializedName("descriptions")
    val descriptions : List<String>?
)

data class ModelVerseDhammapada(
    @SerializedName("verse_number")
    val verseNumber: Int,
    @SerializedName("text")
    val text : List<String>
)