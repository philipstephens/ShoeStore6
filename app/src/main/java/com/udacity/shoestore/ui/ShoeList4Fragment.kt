package com.udacity.shoestore.ui

import android.graphics.Typeface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeList4Binding
import com.udacity.shoestore.models.ShoeViewModel

import timber.log.Timber

class ShoeList4Fragment : Fragment() {

    lateinit var viewModel: ShoeViewModel
    lateinit var binding: FragmentShoeList4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate( inflater,
            R.layout.fragment_shoe_list4,
            container, false)

        showShoeList()

        return binding.root
    }

    private fun showShoeList() {
        var textView: TextView
        val textViewArrayList = ArrayList<TextView>()
        val shoe_map = viewModel.getNameListMap()

        binding.idListShoesTextHeading.text = shoe_map["list_shoes_text_heading"]
        binding.idNameHeading.text = shoe_map["name_heading"]

        var index = 0
        for (shoeName in viewModel.getShoeList()) {
            Timber.d("ShoeName: $shoeName")
            textView = setTextViewData(shoeName)
            textViewArrayList.add(textView)
            binding.idShoeListLayout.addView(textViewArrayList[index])
            setTextViewListener(textViewArrayList[index], index)
            index++
        }

        binding.fab.setOnClickListener {
            viewModel.clearTempForm()
            it.findNavController().navigate(
                ShoeList4FragmentDirections.actionShoeList4FragmentToShoeDetail2Fragment()
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.shoe_list_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if(menuItem.itemId == R.id.mi_logout) {
                   view.findNavController().navigate(
                       ShoeList4FragmentDirections.actionShoeList4FragmentToLoginFragment()
                   )
                }
                return true
            }
        }, viewLifecycleOwner)
    }

    fun setTextViewData(_text: String): TextView {
        return TextView(requireActivity()).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 20, 0, 0)
            }
            text = _text
            setTextColor(ContextCompat.getColor(requireActivity(), R.color.colorHints))
            textSize = 20.0f
            typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        }
    }

    fun setTextViewListener(_textView: TextView, _index: Int) {
        _textView.setOnClickListener {
            viewModel.setFormData(_index)
            Timber.d("Index $_index")
            it.findNavController().navigate(
                ShoeList4FragmentDirections.actionShoeList4FragmentToShoeDetail2Fragment()
            )
        }
    }
}
