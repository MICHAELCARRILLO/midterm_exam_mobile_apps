package com.example.appsuperzound.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "albums"
)
class Album (
    @PrimaryKey
    @SerializedName("idAlbum")
    val id: Int = 0,

    @SerializedName("strAlbum")
    @ColumnInfo
    val name : String,

    @SerializedName("strArtist")
    @ColumnInfo
    val artist : String,

    @SerializedName("strAlbumThumb")
    @ColumnInfo
    val image : String,

    @SerializedName("strAlbum3DCase")
    @ColumnInfo
    val caseImage : String,

    @SerializedName("intYearReleased")
    @ColumnInfo
    val releasedDate : String,

    @SerializedName("intScore")
    @ColumnInfo
    val score : String,

    @SerializedName("strGenre")
    @ColumnInfo
    val gender : String,

    ): Serializable