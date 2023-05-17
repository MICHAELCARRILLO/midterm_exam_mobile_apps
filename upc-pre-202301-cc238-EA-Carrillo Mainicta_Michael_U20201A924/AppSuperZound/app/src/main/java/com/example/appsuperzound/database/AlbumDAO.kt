package com.example.appsuperzound.database

import androidx.room.*
import com.example.appsuperzound.models.Album

@Dao
interface AlbumDAO {

    @Insert
    fun insertAlbum(vararg album: Album)

    @Query("SELECT * FROM albums")
    fun getAllAlbums(): List<Album>

    @Delete
    fun deleteAlbums(vararg album: Album)

    @Update
    fun updateAlbums(vararg album: Album)

    @Query("SELECT * FROM albums WHERE id = :albumId")
    fun getAlbumById(albumId: Int): Album

}