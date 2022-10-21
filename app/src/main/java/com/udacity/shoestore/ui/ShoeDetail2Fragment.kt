package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetail2Binding
import com.udacity.shoestore.models.ShoeViewModel
import timber.log.Timber
import java.time.Duration


class ShoeDetail2Fragment : Fragment() {

    lateinit var viewModel: ShoeViewModel
    lateinit var binding: FragmentShoeDetail2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]

        viewModel.shoeListData.observe(requireActivity(), Observer {
            Toast.makeText(context, "Shoe List Observed", Toast.LENGTH_LONG).show()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail2, container, false
        )

        binding.detailNameHeading.text = viewModel.getDetailHeadingArray()[0]
        binding.detailNameData.hint = viewModel.getDetailHeadingArray()[0]
        binding.detailNameData.setText(viewModel.tempShoeData.value?.name)

        binding.detailCompanyHeading.text = viewModel.getDetailHeadingArray()[1]
        binding.detailCompanyData.hint = viewModel.getDetailHeadingArray()[1]
        binding.detailCompanyData.setText(viewModel.tempShoeData.value?.company)

        binding.detailSizeHeading.text = viewModel.getDetailHeadingArray()[2]
        binding.detailSizeHeading.hint = viewModel.getDetailHeadingArray()[2]
        binding.detailSizeData.setText(viewModel.tempShoeData.value?.size.toString())

        binding.detailDescriptionHeading.text = viewModel.getDetailHeadingArray()[3]
        binding.detailDescriptionHeading.hint = viewModel.getDetailHeadingArray()[3]
        binding.detailDescriptionData.setText(viewModel.tempShoeData.value?.description)

        showLanguage()


        return binding.root
    }

    private fun showLanguage() {
        val languageMap: Map<String, String> = viewModel.getDetailMap()

        binding.detailSaveButton.setText(languageMap["save_button_text"])
        binding.detailCancelButton.setText(languageMap["cancel_button_text"])
        Timber.d("button1: ${binding.detailSaveButton.text}")
        Timber.d("button2: ${binding.detailCancelButton.text}")

        binding.detailSaveButton.setOnClickListener {
            Timber.d("Called detailSaveButton")
            viewModel.saveEditedForm(binding.detailNameData.text.toString(),
                                    binding.detailCompanyData.text.toString(),
                                    binding.detailSizeData.text.toString(),
                                    binding.detailDescriptionData.text.toString())

            viewModel.clearTempForm()

            it.findNavController().navigate(
                ShoeDetail2FragmentDirections.actionShoeDetail2FragmentToShoeList4Fragment()
            )
        }

        binding.detailCancelButton.setOnClickListener {
            it.findNavController().navigate(
                ShoeDetail2FragmentDirections.actionShoeDetail2FragmentToShoeList4Fragment()
            )
        }
    }
}
