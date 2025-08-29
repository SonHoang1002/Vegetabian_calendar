package com.hts.vegetabiancalendar.model

import com.google.gson.annotations.SerializedName

data class ModelDhammapada(
    @SerializedName("title")
    val title: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("documents")
    val chapter: List<ModelChapterDhammapada>
) {
    fun getVerseSize(): Int {
        return chapter.sumOf { it.verses.size }
    }

    fun getVerseByIndex(index: Int): ModelVerseDhammapada? {
        val size = getVerseSize()
        if (size == 0) return null
        val safeIndex = ((index % size) + size) % size
        return getAllVerse()[safeIndex]
    }

    fun getChapterByVerseIndex(index: Int): ModelChapterDhammapada? {
        val totalVerses = getVerseSize()
        if (totalVerses == 0) return null

        // Normalize index to handle negative values and wrap around
        val safeIndex = ((index % totalVerses) + totalVerses) % totalVerses

        var currentIndex = 0

        // Iterate through chapters and accumulate verse counts
        for (chapter in chapter) {
            val chapterVerseCount = chapter.verses.size

            // Check if the target index falls within this chapter's range
            if (safeIndex >= currentIndex && safeIndex < currentIndex + chapterVerseCount) {
                return chapter
            }

            currentIndex += chapterVerseCount
        }

        // This should never happen if the data is consistent
        return null
    }

    fun getAllVerse(): List<ModelVerseDhammapada> {
        val listVerse = ArrayList<ModelVerseDhammapada>()
        for (chapter in chapter) {
            for (verse in chapter.verses) {
                listVerse.add(verse)
            }
        }
        return listVerse
    }
}

data class ModelChapterDhammapada(
    @SerializedName("chapter_number")
    val chapterNumber: Int,
    @SerializedName("chapter_title")
    val chapterTitle: String,
    @SerializedName("verses")
    val verses: List<ModelVerseDhammapada>,
    @SerializedName("descriptions")
    val descriptions: List<String>?
) {
    fun getVerseSize(): Int {
        return verses.size
    }
}


data class ModelVerseDhammapada(
    @SerializedName("verse_number")
    val verseNumber: Int,
    @SerializedName("text")
    val text: List<String>

) {
    fun getTextAndOrder(): List<String> {
        val result = mutableListOf<String>(verseNumber.toString() + ".")
        for (i in 0..text.size - 1) {
            result.add(text[i])
        }
        return result


    }
}