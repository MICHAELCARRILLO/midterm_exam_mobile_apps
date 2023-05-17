package com.example.appsuperzound.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appsuperzound.R
import com.example.appsuperzound.database.AlbumDB
import com.example.appsuperzound.models.Album
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class AlbumFavoriteAdapter(val albums: List<Album>, val context: Context): RecyclerView.Adapter<AlbumFavoriteAdapter.ViewHolder>() {
    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        val tvAlbumName =  view.findViewById<TextView>(R.id.tvAlbumNameFavorite);
        val tvAlbumGender =  view.findViewById<TextView>(R.id.tvAlbumGender);
        val ivAlbumCase = view.findViewById<ImageView>(R.id.ivAlbumCase);
        val fabDelete = view.findViewById<FloatingActionButton>(R.id.fabDelete);
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumFavoriteAdapter.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.prototype_album_favorite, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumFavoriteAdapter.ViewHolder, position: Int) {
        val album = albums[position]
        holder.tvAlbumName.text = album.name
        holder.tvAlbumGender.text = album.gender

        val picBuilder = Picasso.Builder(context)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build().load(album.caseImage).into(holder.ivAlbumCase)

        holder.fabDelete.setOnClickListener {
            //save album in database
            val existingAlbum = AlbumDB.getInstance(context).getAlbumDAO().getAlbumById(album.id)
            if(existingAlbum != null){
                AlbumDB.getInstance(context).getAlbumDAO().deleteAlbums(album)
                albums.toMutableList().remove(album)

                Toast.makeText(context, "Album deleted. Please refresh the page.", Toast.LENGTH_SHORT).show()

            }


        }

    }
}