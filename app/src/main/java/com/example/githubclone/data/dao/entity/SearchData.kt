package com.example.githubclone.data.dao.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_table")
data class SearchData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)