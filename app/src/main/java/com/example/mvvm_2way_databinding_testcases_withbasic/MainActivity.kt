package com.example.mvvm_2way_databinding_testcases_withbasic


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.mvvm_2way_databinding_testcases_withbasic.databinding.ActivityMainBinding
import com.example.repo.MyRepo
import com.example.viewmodel.MainViewModel
import com.example.viewmodel.MyViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModelFactory = MyViewModelFactory(MyRepo())
//        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        binding.vm = mainViewModel
        binding.lifecycleOwner = this
        attachObservers()
        binding.btn.setOnClickListener { clicked() }
    }

    private fun clicked() {
        mainViewModel.getUsers()
    }

    private fun attachObservers() {
        mainViewModel.liveDataList.observe(this) {
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}