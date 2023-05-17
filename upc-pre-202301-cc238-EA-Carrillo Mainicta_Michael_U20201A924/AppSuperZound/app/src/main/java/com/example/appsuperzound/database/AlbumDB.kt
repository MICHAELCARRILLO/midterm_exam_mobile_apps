package com.example.appsuperzound.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appsuperzound.models.Album

@Database(entities = [Album::class], version = 1)
abstract class AlbumDB : RoomDatabase(){
    abstract fun getAlbumDAO() : AlbumDAO

    companion object {

        private var INSTANCE : AlbumDB?= null

        fun getInstance(context: Context) : AlbumDB {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(context, AlbumDB::class.java, "album.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as AlbumDB
        }
    }
}