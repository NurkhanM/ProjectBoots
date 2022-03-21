package com.md.nurkhan.projectboots.fragments.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.md.nurkhan.projectboots.R
import kotlinx.android.synthetic.main.my_toolbar.*

class FavoritFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_favorit, container, false)
        activity?.txttoolbar?.text = "Избранное"


        return view
    }


}