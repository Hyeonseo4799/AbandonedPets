package com.project.abandonedpets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.abandonedpets.databinding.DialogBottomSheetNeuterBinding

class BottomSheetNeuter : BottomSheetDialogFragment() {
    private lateinit var binding: DialogBottomSheetNeuterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_bottom_sheet_neuter, container, false)
        binding.apply {
            fragment = this@BottomSheetNeuter
            lifecycleOwner = this@BottomSheetNeuter
        }
        return binding.root
    }
}