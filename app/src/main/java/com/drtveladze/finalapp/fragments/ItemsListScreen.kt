package com.drtveladze.finalapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.drtveladze.finalapp.adapter.ShoppingListAdapter
import com.drtveladze.finalapp.databinding.FragmentItemsListScreenBinding
import com.drtveladze.finalapp.model.ShopListItem
import com.google.firebase.database.*

class ItemsListScreen : Fragment() {
    private lateinit var binding: FragmentItemsListScreenBinding
    private lateinit var db: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adapter = ShoppingListAdapter(mutableListOf(), onClick = {
            findNavController().navigate(
                ItemsListScreenDirections.actionItemsListScreenToDetailsScreen(
                    it
                )
            )
        })
        db = FirebaseDatabase.getInstance().getReference("items")
        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                children.forEach {
                    val item = it.getValue(ShopListItem::class.java)
                    adapter.items.add(item!!)
                    adapter.notifyDataSetChanged()
                    binding.progressBar.visibility = View.GONE
                }
            }
        })

        binding = FragmentItemsListScreenBinding.inflate(layoutInflater)

        binding.itemsRV.layoutManager = LinearLayoutManager(context)
        binding.itemsRV.adapter = adapter

        return binding.root
    }
}