package com.drtveladze.finalapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.drtveladze.finalapp.databinding.FragmentHomeScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeScreen : Fragment() {
    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(layoutInflater)
        auth = Firebase.auth
        binding.loginTV.setOnClickListener {
            findNavController().navigate(HomeScreenDirections.actionHomeScreenToLoginScreen())
        }

        binding.resetPass.setOnClickListener {
            findNavController().navigate(HomeScreenDirections.actionHomeScreenToForgotPassword())
        }

        binding.registerButton.setOnClickListener {
            if (checkInputsAreNotEmpty(
                    binding.emailET.text.toString(),
                    binding.passwordET.toString(),
                    binding.repeatPasswordET.toString()
                ) && repeatPasswordMatchesPassword(
                    binding.passwordET.text.toString(),
                    binding.repeatPasswordET.text.toString()
                )
            ) {
                auth.createUserWithEmailAndPassword(
                    binding.emailET.text.toString(),
                    binding.passwordET.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(HomeScreenDirections.actionHomeScreenToLoginScreen())

                    } else {
                        Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "something is wrong with the input", Toast.LENGTH_SHORT)
                    .show()
            }


        }
        return binding.root
    }

    private fun checkInputsAreNotEmpty(
        email: String,
        password: String,
        repeatPassword: String
    ): Boolean {
        return email.isNotEmpty() && password.isNotEmpty() && repeatPassword.isNotEmpty()
    }

    private fun repeatPasswordMatchesPassword(password: String, repeatPassword: String): Boolean {
        return password == repeatPassword
    }
}