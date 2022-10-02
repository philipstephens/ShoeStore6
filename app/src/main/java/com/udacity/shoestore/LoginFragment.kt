package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.udacity.shoestore.databinding.FragmentLoginBinding

// default language: English
private const val ARG_LANGUAGE = "language"

class LoginFragment : Fragment()   {
    private var paramLanguage: String? = "en"

    private val langText = mapOf(
        "en_create_button_title" to "Create Account",
        "en_hint_email_address" to "Enter email address",
        "en_hint_password" to "Enter password",
        "en_login_button_title" to "Login",
        "en_login_email_title" to "Email Address",
        "en_password_title" to "Password",
        "fr_create_button_title" to "Créer un compte",
        "fr_hint_email_address" to "Entrer l\'adresse e-mail",
        "fr_hint_password" to "Entrer le mot de passe",
        "fr_login_button_title" to "l\' ouverture de session",
        "fr_login_email_title" to "l\' adresse électronique",
        "fr_login_email_title" to "l\' adresse électronique",
        "fr_password_title" to "Mot de passe"
    )

    //Inflating and Returning the View with DataBindingUtil
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        paramLanguage = if (savedInstanceState != null) {
            savedInstanceState.getString(ARG_LANGUAGE)
        } else {
            "en"  // English is the default language
        }

        when (paramLanguage) {
            "en" -> bindEnglish(binding)
            "fr" -> bindFrench(binding)
        }

        binding.idImageFrFlag.setOnClickListener {
            bindFrench(binding)
        }

        binding.idImageEnFlag.setOnClickListener {
            bindEnglish(binding)
        }

        binding.idCreateButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_welcomeFragment))

        binding.idLoginButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_welcomeFragment))

        return binding.root
    }

    private fun bindFrench(binding: FragmentLoginBinding) {
        binding.idCreateButton.text = langText["fr_create_button_title"]
        binding.idEmailField.hint = langText["fr_hint_email_address"]
        binding.idEmailTitle.text = langText["fr_login_email_title"]
        binding.idLoginButton.text = langText["fr_login_button_title"]
        binding.idPasswordField.hint = langText["fr_hint_password"]
        binding.idPasswordTitle.text = langText["fr_password_title"]
    }

    private fun bindEnglish(binding: FragmentLoginBinding) {
        binding.idCreateButton.text = langText["en_create_button_title"]
        binding.idEmailField.hint = langText["en_hint_email_address"]
        binding.idEmailTitle.text = langText["en_login_email_title"]
        binding.idLoginButton.text = langText["en_login_button_title"]
        binding.idPasswordField.hint = langText["en_hint_password"]
        binding.idPasswordTitle.text = langText["en_password_title"]
    }
}
