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
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeList4Binding
import com.udacity.shoestore.models.ShoeViewModel

import timber.log.Timber

class ShoeList4Fragment : Fragment() {

    lateinit var shoeViewModel: ShoeViewModel
    lateinit var binding: FragmentShoeList4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shoeViewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate( inflater,
            R.layout.fragment_shoe_list4,
            container, false)

        shoeViewModel.shoeListDataValue.observe(requireActivity(), Observer {
            var i = 0
            var textView: TextView
            binding.idShoeListLayout.removeAllViews()

            if (it.size > 0) {
                for (shoe in it) {
                    // stop here
                    textView = setTextViewData(shoe.name)
                    binding.idShoeListLayout.addView(textView)
                    setTextViewListener(textView, i)
                }
            }
        })

        binding.fab.setOnClickListener {
            shoeViewModel.clearTempForm()
            it.findNavController().navigate(
                ShoeList4FragmentDirections.actionShoeList4FragmentToShoeDetail2Fragment()
            )
        }

        return binding.root
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
            shoeViewModel.setFormData(_index)
            Timber.d("Index $_index")
            it.findNavController().navigate(
                ShoeList4FragmentDirections.actionShoeList4FragmentToShoeDetail2Fragment()
            )
        }
    }
}
