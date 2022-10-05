package com.udacity.shoestore

import android.content.Intent
import android.content.Intent.ACTION_MAIN
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.models.ShoeViewModel

class InstructionsFragment : Fragment() {
    val ARG_LANGUAGE = "language"
    var viewModel = ShoeViewModel()
    lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_instructions, container, false
        )

        viewModel = ViewModelProvider(this)[ShoeViewModel::class.java]

        arguments?.let {
            viewModel.setLanguage(it.getString(ARG_LANGUAGE) ?: "en")
            Toast.makeText(context, viewModel.getLanguage() , Toast.LENGTH_SHORT).show()
            showLanguage()
        }

        binding.listShoesButton.setOnClickListener {
            context?.let{
                val intent = Intent(it, ShoeListActivity3::class.java)
                startActivity(intent)
            }
        }

        return binding.root
    }

    private fun showLanguage() {
        val languageMap: Map<String, String> = viewModel.getInstructionsMap(viewModel.getLanguage())

        binding.listShoesButton.text = languageMap["instructions_button_text"]
        binding.idInstructionsHeading.text = languageMap["instructions_heading"]
        binding.idInstructionsText1.text = languageMap["instructions_text1"]
        binding.idInstructionsText2.text = languageMap["instructions_text2"]
        binding.idInstructionsText3.text = languageMap["instructions_text3"]
    }
}

