package com.udacity.shoestore

import android.content.Context
import androidx.navigation.Navigation
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.models.ShoeViewModel

class WelcomeFragment : Fragment() {
    lateinit var viewModel: ShoeViewModel
    lateinit var binding: FragmentWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false)

        binding.idInstructionsButton.setOnClickListener {
            it.findNavController().navigate(
                WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment()
            )
        }

        showLanguage()

        return binding.root
    }

    private fun showLanguage() {
        val languageMap: Map<String, String> = viewModel.getWelcomeMap()

        binding.idWelcomeTextHeading.text = languageMap["welcome_heading"]
        binding.idWelcomeText1.text = languageMap["welcome_text1"]
        binding.idWelcomeText1b.text = languageMap["welcome_text1b"]
        binding.idWelcomeText2.text = languageMap["welcome_text2"]
        binding.idWelcomeText2List1.text = languageMap["welcome_text2_list1"]
        binding.idWelcomeText2List2.text = languageMap["welcome_text2_list2"]
        binding.idWelcomeText2List3.text = languageMap["welcome_text2_list3"]
        binding.idInstructionsButton.text = languageMap["welcome_button_text"]
    }
}
