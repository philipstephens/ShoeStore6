package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// default language: English
private const val ARG_LANGUAGE = "en"

class LoginFragment : Fragment() {
    private var paramLanguage: String? = "en"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramLanguage = it.getString(ARG_LANGUAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
        /**
         *
         * @param _language Language of Application currently English, French
         */
        @JvmStatic
        fun newInstance(_language: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LANGUAGE, _language)
                }
            }
    }
}