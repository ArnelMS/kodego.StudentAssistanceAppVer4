package com.kodego.activity.app.studentassistanceappver4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.activity.app.studentassistanceappver4.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //DATA SOURCE
        var subjectsList = mutableListOf<Subjects>(
            Subjects(R.drawable.biologyicon,"Biology","M-W-F","08:00 AM - 10:00 AM", "Prof. Aristotle"),
            Subjects(R.drawable.chemistryicon,"Chemistry","M-W-F","10:00 AM - 12:00 NN", "Prof. Antoine-Laurent de Lavoisier"),
            Subjects(R.drawable.designicon,"Art","M-W-F","01:PM PM - 03:00 PM", "Prof. Pablo Picasso"),
            Subjects(R.drawable.englishicon,"English","M-W-F","03:00 PM - 05:00 PM", "Prof. Geoffrey Chaucer"),
            Subjects(R.drawable.historyicon,"History","T-TH-S","08:00 AM - 10:00 AM", "Prof. Herodotus"),
            Subjects(R.drawable.infotechicon,"I.T.","T-TH-S","10:00 AM - 12:00 NN", "Prof. Claude Elwood Shannon"),
            Subjects(R.drawable.mathicon,"Mathematics","T-TH-S","01:00 PM - 03:00 PM", "Prof. Archimedes"),
            Subjects(R.drawable.scienceicon,"Science","T-TH-S","03:00 PM - 05:00 PM", "Prof. Galileo Galilei")
        )
        //PASS DATA SOURCE TO ADAPTER
        val adapter = SubjectsAdapter(subjectsList)
        adapter.onItemClick = {
            val intent = Intent(this, SubjectDetailActivity::class.java)
            intent.putExtra("imageSubjectName",it.imageSubjectName)
            intent.putExtra("itemSubjectName",it.itemSubjectName)
            intent.putExtra("itemSubjectSchedule",it.itemSubjectSchedule)
            intent.putExtra("itemSubjectTime",it.itemSubjectTime)
            intent.putExtra("itemSubjectProfessor",it.itemSubjectProfessor)



            Toast.makeText(applicationContext,it.itemSubjectName, Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        binding.recylerHome.adapter = adapter
        binding.recylerHome.layoutManager = LinearLayoutManager(this)


    }
}