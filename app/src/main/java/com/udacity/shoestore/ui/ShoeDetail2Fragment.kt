package com.udacity.shoestore.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetail2Binding
import com.udacity.shoestore.databinding.FragmentShoeDetailBindingImpl
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.models.ShoeViewModel

class ShoeDetail2Fragment : Fragment() {

    lateinit var viewModel: ShoeViewModel
    lateinit var binding: FragmentShoeDetail2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail2, container, false
        )

        binding.detailNameHeading.text = viewModel.getDetailHeadingArray()[0]
        binding.detailNameData.hint = viewModel.tempShoeData.value?.name

        binding.detailSizeHeading.text = viewModel.getDetailHeadingArray()[1]
        binding.detailSizeData.hint = viewModel.tempShoeData.value?.size.toString()

        binding.detailCompanyHeading.text = viewModel.getDetailHeadingArray()[2]
        binding.detailCompanyData.hint = viewModel.tempShoeData.value?.company

        binding.detailDescriptionHeading.text = viewModel.getDetailHeadingArray()[3]
        binding.detailDescriptionData.hint = viewModel.tempShoeData.value?.description

        return binding.root
    }
}
