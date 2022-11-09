package com.kodego.activity.app.studentassistanceappver4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodego.activity.app.studentassistanceappver4.databinding.ActivityStudentProfileBinding

class StudentProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityStudentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}