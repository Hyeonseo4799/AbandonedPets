package com.project.abandonedpets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.abandonedpets.databinding.DialogBottomSheetSpeciesBinding

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
        return binding.root
    }
}