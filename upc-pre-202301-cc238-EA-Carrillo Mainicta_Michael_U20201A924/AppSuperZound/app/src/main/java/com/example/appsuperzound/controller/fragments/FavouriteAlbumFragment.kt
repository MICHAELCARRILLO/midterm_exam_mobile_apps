package com.example.appsuperzound.controller.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsuperzound.R
import com.example.appsuperzound.adapter.AlbumFavoriteAdapter
import com.example.appsuperzound.database.AlbumDB
import com.example.appsuperzound.models.Album

class FavouriteAlbumFragment : Fragment() {


    lateinit var albums : ArrayList<Album>
    lateinit var recyclerView: RecyclerView
    lateinit var albumAdapter: AlbumFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_album, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albums = ArrayList()
        recyclerView = view.findViewById(R.id.rvFavoriteAlbums)
        albumAdapter = AlbumFavoriteAdapter(albums, view.context)

        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = albumAdapter

        loadAlbums(view.context)

    }

    private fun loadAlbums(context: Context) {
        val newAlbums = AlbumDB.getInstance(context).getAlbumDAO().getAllAlbums()
        albums.addAll(newAlbums)
        albumAdapter.notifyDataSetChanged()
    }



}