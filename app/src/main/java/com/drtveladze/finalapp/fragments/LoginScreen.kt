package com.drtveladze.finalapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.drtveladze.finalapp.databinding.FragmentLoginScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginScreen : Fragment() {
    lateinit var binding: FragmentLoginScreenBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginScreenBinding.inflate(layoutInflater)
        auth = Firebase.auth
        binding.loginButton.setOnClickListener {
            if (binding.emailET.text.toString().isNotEmpty() && binding.passwordET.text.toString()
                    .isNotEmpty()
            ) {
                auth.signInWithEmailAndPassword(
                    binding.emailET.text.toString(),
                    binding.passwordET.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(LoginScreenDirections.actionLoginScreenToItemsListScreen())
                    } else {
                        Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Enter e-mail and password", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}