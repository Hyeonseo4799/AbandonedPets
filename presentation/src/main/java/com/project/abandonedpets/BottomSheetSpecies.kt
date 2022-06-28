package com.project.abandonedpets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.abandonedpets.databinding.DialogBottomSheetSpeciesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class BottomSheetSpecies : BottomSheetDialogFragment() {
    private lateinit var binding: DialogBottomSheetSpeciesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_bottom_sheet_species, container, false)
        binding.apply {
            fragment = this@BottomSheetSpecies
            lifecycleOwner = this@BottomSheetSpecies
        }

        CoroutineScope(Dispatchers.IO).launch {
            setRadio(MyApplication.getInstance().getDataStore().species.first())
        }

        return binding.root
    }

    fun select(view: View) {
        binding.apply {
            when (view) {
                rbDog -> save("dog")
                rbCat -> save("cat")
                rbAll -> save("all")

            }
        }
    }

    private fun setRadio(data: String) {
        CoroutineScope(Dispatchers.IO).launch {
            binding.apply {
                when (data) {
                    "dog" -> rbDog.isChecked = true
                    "cat" -> rbCat.isChecked = true
                    "all" -> rbAll.isChecked = true
                }
            }
        }
    }

    private fun save(value: String) {
        CoroutineScope(Dispatchers.IO).launch {
            MyApplication.getInstance().getDataStore().save(value, "species")
        }
    }
}