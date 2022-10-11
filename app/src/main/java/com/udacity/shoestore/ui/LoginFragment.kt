package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.models.ShoeViewModel

// default language: English

class LoginFragment : Fragment()   {
    lateinit var viewModel: ShoeViewModel
    lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)

        viewModel.language.observe(this, Observer {
            showLanguage()
        })

    }

    //Inflating and Returning the View with DataBindingUtil
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        binding.idImageFrFlag.setOnClickListener {
            viewModel.language.value = "fr"
        }

        binding.idImageEnFlag.setOnClickListener {
            viewModel.language.value = "en"
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

        showLanguage()

        return binding.root
    }

    private fun showLanguage() {
        val languageMap: Map<String, String> = viewModel.getLoginMap()

        binding.idCreateButton.text = languageMap["create_button_title"]
        binding.idEmailField.hint = languageMap["hint_email_address"]
        binding.idEmailTitle.text = languageMap["login_email_title"]
        binding.idLoginButton.text = languageMap["login_button_title"]
        binding.idPasswordField.hint = languageMap["hint_password"]
        binding.idPasswordTitle.text = languageMap["password_title"]
    }
}
