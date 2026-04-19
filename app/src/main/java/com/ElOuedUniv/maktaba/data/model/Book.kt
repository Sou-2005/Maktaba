package com.ElOuedUniv.maktaba.data.model

data class Book(
    val isbn: String,
    val id: String = isbn,
    val title: String,
    val nbPages: Int,
    val currentPage: Int = 0,
    val imageUrl: String? = null,
    val readingStatus: ReadingStatus = ReadingStatus.NOT_STARTED
)

enum class ReadingStatus(val label: String) {
    NOT_STARTED("Not Started"),
    READING("Reading"),
    FINISHED("Finished")
}
