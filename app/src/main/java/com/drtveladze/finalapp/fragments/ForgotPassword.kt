package com.drtveladze.finalapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.drtveladze.finalapp.databinding.FragmentResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPassword : Fragment() {
    private lateinit var binding: FragmentResetPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = Firebase.auth
        binding = FragmentResetPasswordBinding.inflate(layoutInflater)
        binding.resetPass.setOnClickListener {
            if (binding.emailET.text.isNotEmpty()) {
                auth.sendPasswordResetEmail(binding.emailET.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
                            val action =
                                ForgotPasswordDirections.actionForgotPasswordToLoginScreen()
                            findNavController().navigate(action)
                        } else {
                            Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            } else {
                Toast.makeText(context, "Enter e-mail", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}