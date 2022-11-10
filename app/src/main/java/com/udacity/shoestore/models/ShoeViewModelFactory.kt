package com.udacity.shoestore.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ShoeViewModelFactory(private val _language : String) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeViewModel::class.java)){
            return ShoeViewModel(_language) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}