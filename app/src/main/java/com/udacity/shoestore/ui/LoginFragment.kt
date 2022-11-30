package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.models.ShoeViewModel
import com.udacity.shoestore.models.ShoeViewModelFactory

// default language: English

class LoginFragment : Fragment()   {
    private val shoeViewModel by viewModels<ShoeViewModel>(factoryProducer = {
        ShoeViewModelFactory("en")
    })

    lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shoeViewModel.languageValue.observe(requireActivity()) {
            showLanguage()
        }
    }

    //Inflating and Returning the View with DataBindingUtil
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        shoeViewModel.languageValue.observe(requireActivity()) {
            showLanguage()
        }

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        binding.idImageFrFlag.setOnClickListener {
            shoeViewModel.setLanguage("fr")
        }

        binding.idImageEnFlag.setOnClickListener {
            shoeViewModel.setLanguage("en")
        }

        binding.idCreateButton.setOnClickListener {
            it.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
            )
        }

        binding.idLoginButton.setOnClickListener {
            it.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }

        return binding.root
    }

    private fun showLanguage() {
        val languageMap: Map<String, String> = shoeViewModel.getLoginMap()

        binding.idCreateButton.text = languageMap["create_button_title"]
        binding.idEmailField.hint = languageMap["hint_email_address"]
        binding.idEmailTitle.text = languageMap["login_email_title"]
        binding.idLoginButton.text = languageMap["login_button_title"]
        binding.idPasswordField.hint = languageMap["hint_password"]
        binding.idPasswordTitle.text = languageMap["password_title"]
    }
}
