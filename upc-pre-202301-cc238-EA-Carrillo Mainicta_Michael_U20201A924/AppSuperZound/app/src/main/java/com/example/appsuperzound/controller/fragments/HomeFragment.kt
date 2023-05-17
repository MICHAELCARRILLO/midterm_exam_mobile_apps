package com.example.appsuperzound.controller.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.appsuperzound.R

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val ivLogo = view.findViewById<ImageView>(R.id.ivLogo);
        ivLogo.setImageResource(R.drawable.logo);

        return view
    }


}