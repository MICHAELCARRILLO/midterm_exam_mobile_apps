package com.example.appsuperzound.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.appsuperzound.R
import com.example.appsuperzound.database.AlbumDB
import com.example.appsuperzound.models.Album
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso


class AlbumAdapter(val albums: List<Album>, val context: Context): RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        val albumName = view.findViewById<TextView>(R.id.tvAlbumName);
        val artistName =  view.findViewById<TextView>(R.id.tvArtistName);
        val albumImage = view.findViewById<ImageView>(R.id.ivAlbumImage);
        val albumYear = view.findViewById<TextView>(R.id.tvReleasedDate);
        val albumScore = view.findViewById<RatingBar>(R.id.rbScore);
        val fabFavorite = view.findViewById<FloatingActionButton>(R.id.fabFavorite);
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.prototype_album, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumAdapter.ViewHolder, position: Int) {
        val album = albums[position]

        holder.albumName.text = album.name
        holder.artistName.text = album.artist
        holder.albumYear.text = album.releasedDate.toString()
        holder.albumScore.rating = album.score.toFloat()

        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build().load(album.image).into(holder.albumImage)

        holder.fabFavorite.setOnClickListener {
            //save album in database

            val existingAlbum = AlbumDB.getInstance(context).getAlbumDAO().getAlbumById(album.id)
            if(existingAlbum == null){
                AlbumDB.getInstance(context).getAlbumDAO().insertAlbum(album)
                Toast.makeText(context, "Album added to Favorite.", Toast.LENGTH_SHORT).show()
            }
        }



    }
}