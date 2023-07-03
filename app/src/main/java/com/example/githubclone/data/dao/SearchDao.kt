package com.example.githubclone.data.dao

import androidx.room.*
import com.example.githubclone.data.dao.entity.SearchData

@Dao
interface SearchDao {

    @Query("SELECT * FROM search_table")
    suspend fun getAllSearch(): MutableList<SearchData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSearch(searchData: SearchData)

    @Delete
    suspend fun deleteSearch(searchData: SearchData)
}