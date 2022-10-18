package com.udacity.shoestore.ui

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.databinding.FragmentShoeList4Binding
import com.udacity.shoestore.models.ShoeViewModel
import timber.log.Timber

class ShoeDetailFragment : Fragment() {
    lateinit var viewModel: ShoeViewModel
    lateinit var binding: FragmentShoeDetailBinding
    lateinit var detHeading: Array<String>
    lateinit var tvHeading: ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)
        detHeading = viewModel.getDetailHeadingArray()
        tvHeading = ArrayList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
            binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_shoe_detail, container,false)

            setCardViewData()
            Timber.d("Hi!")
        return binding.root
    }

    fun setCardViewData() {
        binding.detailedCardView.apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            ).apply {
                setMargins(10,10,10,10)
            }
        }
        binding.detailedCardView.cardElevation = 10f
        binding.detailedCardView.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.colorPrimary))
        binding.detailedCardView.setContentPadding(10, 0, 10, 10)

        for(i in 0..detHeading.lastIndex) {
            Timber.d("Loop $i")
            if(i == 0) {
                tvHeading.add(
                    setTextViewData(detHeading[i],
                        resources.getDimension(R.dimen.heading1).toInt(),
                        ContextCompat.getColor(requireContext(), R.color.colorHeading1)
                    )
                )
            } else {
                tvHeading.add(setTextViewData(detHeading[i],
                    resources.getDimension(R.dimen.heading2).toInt(),
                    ContextCompat.getColor(requireContext(), R.color.colorHeading2)
                    )
                )
            }
            binding.detailedCardView.addView(tvHeading[i])
        }
    }

    fun setTextViewData(_text: String, _textSize: Int, _textColor: Int): TextView {
        val width = LinearLayout.LayoutParams.MATCH_PARENT
        val height = LinearLayout.LayoutParams.MATCH_PARENT
        val layoutParams = LinearLayout.LayoutParams(width, height)

        return TextView(requireActivity()).apply {
            layoutParams.apply {
                setMargins(0, 20, 0, 0)
            }
            text = _text
            setTextColor(_textColor)
            setTextSize(_textSize.toFloat())
            typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        }
    }
}