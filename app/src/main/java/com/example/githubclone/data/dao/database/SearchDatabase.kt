package com.example.githubclone.data.dao.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubclone.App
import com.example.githubclone.data.dao.SearchDao
import com.example.githubclone.data.dao.entity.SearchData

@Database(entities = [SearchData::class], version = 2)
abstract class SearchDatabase: RoomDatabase() {

    abstract fun getSearchDao(): SearchDao

    companion object {
        const val DATABASE_NAME = "db_name"

        fun getInstance(): SearchDatabase {
            return Room.databaseBuilder(
                App.instance, SearchDatabase::class.java, DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}
