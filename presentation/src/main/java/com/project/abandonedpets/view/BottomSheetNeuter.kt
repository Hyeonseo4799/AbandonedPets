package com.project.abandonedpets.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.abandonedpets.MyApplication
import com.project.abandonedpets.R
import com.project.abandonedpets.databinding.DialogBottomSheetNeuterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

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

        CoroutineScope(Dispatchers.IO).launch {
            setRadio(MyApplication.getInstance().getDataStore().neuter.first())
        }

        return binding.root
    }

    fun select(view: View) {
        binding.apply {
            when (view) {
                rbNeutered -> save("neutered")
                rbUnneutered -> save("unneutered")
                rbAll -> save("all")

            }
        }
    }

    private fun setRadio(data: String) {
        CoroutineScope(Dispatchers.IO).launch {
            binding.apply {
                when (data) {
                    "neutered" -> rbNeutered.isChecked = true
                    "unneutered" -> rbUnneutered.isChecked = true
                    "all" -> rbAll.isChecked = true
                }
            }
        }
    }

    private fun save(value: String) {
        CoroutineScope(Dispatchers.IO).launch {
            MyApplication.getInstance().getDataStore().save(value, "neuter")
        }
    }
}