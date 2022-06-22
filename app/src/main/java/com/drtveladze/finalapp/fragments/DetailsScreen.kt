package com.drtveladze.finalapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.drtveladze.finalapp.databinding.FragmentDetailsScreenBinding

class DetailsScreen : Fragment() {
    private lateinit var binding: FragmentDetailsScreenBinding
    private val item: DetailsScreenArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsScreenBinding.inflate(layoutInflater)
        binding.title.text = item.shopItem.name
        binding.description.text = item.shopItem.description
        Glide.with(binding.image).load(item.shopItem.image).into(binding.image)
        binding.priceTV.text = item.shopItem.price
        binding.buybtn.setOnClickListener {
            findNavController().navigate(DetailsScreenDirections.actionDetailsScreenToPayScreen(item.shopItem))
        }
        return binding.root
    }

}