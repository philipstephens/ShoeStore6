package com.udacity.shoestore

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.models.ShoeViewModel

// default language: English

class LoginFragment : Fragment()   {
    val ARG_LANGUAGE = "language"
    private var language: String = "en"
    var viewModel = ShoeViewModel()
    lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.language.observe(this, Observer { newLanguage ->
            viewModel.setLanguage(newLanguage)
            showLanguage()
        })
    }

    //Inflating and Returning the View with DataBindingUtil
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        showLanguage()

        binding.idImageFrFlag.setOnClickListener {
            viewModel.setLanguage("fr")
            showLanguage()
        }

        binding.idImageEnFlag.setOnClickListener {
            viewModel.setLanguage("en")
            showLanguage()
        }

        binding.idCreateButton.setOnClickListener {
            it.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(viewModel.getLanguage()))
        }

        binding.idLoginButton.setOnClickListener {
            it.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(viewModel.getLanguage()))
        }

        return binding.root
    }

    private fun showLanguage() {
        val languageMap: Map<String, String> = viewModel.getLoginMap(viewModel.getLanguage())

        binding.idCreateButton.text = languageMap["create_button_title"]
        binding.idEmailField.hint = languageMap["hint_email_address"]
        binding.idEmailTitle.text = languageMap["login_email_title"]
        binding.idLoginButton.text = languageMap["login_button_title"]
        binding.idPasswordField.hint = languageMap["hint_password"]
        binding.idPasswordTitle.text = languageMap["password_title"]
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param pLanguage Parameter.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(pLanguage: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LANGUAGE, pLanguage)
                }
            }
    }
}
