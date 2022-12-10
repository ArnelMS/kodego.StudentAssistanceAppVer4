package com.kodego.activity.app.studentassistanceappver4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kodego.activity.app.studentassistanceappver4.databinding.ActivitySubjectDetailBinding

class SubjectDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivitySubjectDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //DATA FROM HOME ACTIVITY
        var imageSubjectName: Int = intent.getIntExtra("imageSubjectName", 0)
        var itemSubjectName: String? = intent.getStringExtra("itemSubjectName")
        var itemSubjectDescription: String? = intent.getStringExtra("itemSubjectDescription")
        var itemSubjectSchedule: String? = intent.getStringExtra("itemSubjectSchedule")
        var itemSubjectTime: String? = intent.getStringExtra("itemSubjectTime")
        var itemSubjectProfessor: String? = intent.getStringExtra("itemSubjectProfessor")

            binding.imgSubjectName2.setImageResource(imageSubjectName)
            binding.txtSubjectName2.text = itemSubjectName
            binding.itemSubjectDescription2.text = itemSubjectDescription
            binding.itemSubjectSchedule2.text = itemSubjectSchedule
            binding.itemSubjectTime2.text = itemSubjectTime
            binding.itemSubjectProfessor2.text = itemSubjectProfessor.toString()


    }
}