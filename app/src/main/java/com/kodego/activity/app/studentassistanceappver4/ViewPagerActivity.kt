package com.kodego.activity.app.studentassistanceappver4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodego.activity.app.studentassistanceappver4.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {

    lateinit var binding : ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}