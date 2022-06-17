package com.project.abandonedpets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.abandonedpets.databinding.ActivityMainBinding
import com.project.abandonedpets.viewmodel.MainViewModel
import com.project.domain.model.AbandonedPets
import com.project.domain.model.PageInfo
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.lifecycleOwner = this@MainActivity

        val adapter = setRec()
        val abandonedPetsObserver = Observer<List<AbandonedPets>> {
            adapter.apply {
                abandonedPetsList = it
                notifyDataSetChanged()
            }
        }
        val pageInfoObserver = Observer<PageInfo> { binding.page = it }

        viewModel.getInfo("20220606", "20220614", "1", "50").observe(this@MainActivity, abandonedPetsObserver)
        viewModel.getPageInfo("20220606", "20220614", "1", "10").observe(this@MainActivity, pageInfoObserver)
    }

    private fun setRec(): MainAdapter {
        val adapter = MainAdapter(this@MainActivity)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        return adapter
    }
}