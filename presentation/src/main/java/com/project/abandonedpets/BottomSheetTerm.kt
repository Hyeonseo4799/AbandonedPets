package com.project.abandonedpets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.abandonedpets.databinding.DialogBottomSheetTermBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class BottomSheetTerm : BottomSheetDialogFragment() {
    private lateinit var binding: DialogBottomSheetTermBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_bottom_sheet_term, container, false)
        binding.apply {
            fragment = this@BottomSheetTerm
            lifecycleOwner = this@BottomSheetTerm
        }

        CoroutineScope(Dispatchers.IO).launch {
            setRadio(MyApplication.getInstance().getDataStore().term.first())
        }

        return binding.root
    }

    fun select(view: View) {
        binding.apply {
            when (view) {
                rbDay -> save("day")
                rbWeek -> save("week")
                rbMonth -> save("month")
                rbAll -> save("all")
            }
        }
    }

    private fun setRadio(data: String) {
        CoroutineScope(Dispatchers.IO).launch {
            binding.apply {
                when (data) {
                    "day" -> rbDay.isChecked = true
                    "week" -> rbWeek.isChecked = true
                    "month" -> rbMonth.isChecked = true
                    "all" -> rbAll.isChecked = true
                }
            }
        }
    }

    private fun save(value: String) {
        CoroutineScope(Dispatchers.IO).launch {
            MyApplication.getInstance().getDataStore().save(value, "term")
        }
    }
}