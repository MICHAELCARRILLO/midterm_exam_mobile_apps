package com.example.appsuperzound.controller.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsuperzound.R
import com.example.appsuperzound.adapter.AlbumAdapter
import com.example.appsuperzound.models.Album
import com.example.appsuperzound.models.ApiResponseDetails
import com.example.appsuperzound.network.AlbumService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListAlbumFragment : Fragment() {

    var albums: List<Album> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_album, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albums = ArrayList()
        recyclerView = view.findViewById(R.id.rvListAlbum)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AlbumAdapter(albums, view.context)

        loadAlbums(view.context)

    }

    private fun loadAlbums(context: Context) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://theaudiodb.com/api/v1/json/523532/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val albumService: AlbumService
        albumService = retrofit.create(AlbumService::class.java)

        val request = albumService.getAlbums()

        request.enqueue(object : Callback<ApiResponseDetails> {
            override fun onFailure(call: Call<ApiResponseDetails>, t: Throwable) {
                Log.d("Activity Fail", "Error: "+t.toString())
            }

            override fun onResponse(call: Call<ApiResponseDetails>, responseDetails: Response<ApiResponseDetails>) {
                if (responseDetails.isSuccessful) {
                    val newAlbums: List<Album> = responseDetails.body()!!.albums ?: ArrayList()
                    albums = newAlbums
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = AlbumAdapter(albums, context)
                }

                else{
                    Log.d("Activity Fail", "Error: "+responseDetails.code())
                }
            }
        })
    }


}