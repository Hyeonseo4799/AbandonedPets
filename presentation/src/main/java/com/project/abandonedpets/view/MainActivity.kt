package com.project.abandonedpets.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.abandonedpets.BottomSheetGender
import com.project.abandonedpets.BottomSheetNeuter
import com.project.abandonedpets.BottomSheetSpecies
import com.project.abandonedpets.R
import com.project.abandonedpets.databinding.ActivityMainBinding
import com.project.abandonedpets.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainAdapter: MainAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private lateinit var startDate: String
    private lateinit var endDate: String
    private lateinit var bottomSheetFragment: BottomSheetDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.lifecycleOwner = this@MainActivity
        binding.activity = this@MainActivity

        initAdapter()
        initAbandonedPetsJob()
    }

    private fun initAdapter() {
        val linearLayoutManager = LinearLayoutManager(this@MainActivity)

        mainAdapter = MainAdapter(this@MainActivity)
        binding.recyclerview.apply {
            adapter = mainAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun getDate(param: Int = 0): String {
        val cal = Calendar.getInstance()
        val df = SimpleDateFormat("yyyyMMdd")

        cal.add(Calendar.DATE, param)
        return df.format(cal.time)
    }

    private fun initAbandonedPetsJob() {
        startDate = getDate(-7)
        endDate = getDate()

        viewModel.getPageInfo(startDate, endDate).observe(this@MainActivity) {
            binding.page = it
        }

        lifecycleScope.launch {
            viewModel.getInfo(startDate, endDate).collectLatest {
                mainAdapter.submitData(it)
            }
        }
    }

    fun scrollToTop() {
        binding.recyclerview.smoothScrollToPosition(0)
    }

    fun showBottomSheet(view: View) {
        when (view.id) {
            R.id.species -> bottomSheetFragment = BottomSheetSpecies()
            R.id.address -> {}
            R.id.gender -> bottomSheetFragment = BottomSheetGender()
            R.id.neuter -> bottomSheetFragment = BottomSheetNeuter()

        }
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

}