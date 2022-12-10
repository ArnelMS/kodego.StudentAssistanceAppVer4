package com.kodego.activity.app.studentassistanceappver4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.kodego.activity.app.studentassistanceappver4.databinding.ActivityStudentDashboardBinding

class StudentDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var myData1 = "This is Data 1"
        var myData2 = "This is Data 2"
        var myData3 = "This is Data 3"
        var myData4 = "This is Data 4"

        val fragmentAnnouncement = FragmentOne()
        val fragmentAssignment = FragmentTwo()
        val fragmentNotes = FragmentThree()
        val fragmentSyllabus = FragmentFour()

        var bundle1 = Bundle()
        bundle1.putString("data1", myData1)
        fragmentAnnouncement.arguments = bundle1

        var bundle2 = Bundle()
        bundle2.putString("data2", myData2)
        fragmentAssignment.arguments = bundle2

        var bundle3 = Bundle()
        bundle3.putString("data3", myData3)
        fragmentNotes.arguments = bundle3

        var bundle4 = Bundle()
        bundle4.putString("data4", myData4)
        fragmentSyllabus.arguments = bundle4

        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentMain.id, fragmentAnnouncement)
            commit()
        }

//        binding.tabLayoutDash.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener)
//        override fun onTabSelected(tab: TabLayout.Tab?) {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.fragmentMain.id, fragmentAnnouncement)
                commit()

                binding.tabAnnouncement.setOnClickListener() {
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.fragmentMain.id, fragmentAnnouncement)
                        commit()
                    }
                }
                binding.tabAssignment.setOnClickListener() {
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.fragmentMain.id, fragmentAssignment)
                        commit()
                    }
                }
                binding.tabNotes.setOnClickListener() {
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.fragmentMain.id, fragmentNotes)
                        commit()
                    }
                }
                binding.tabSyllabus.setOnClickListener() {
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.fragmentMain.id, fragmentSyllabus)
                        commit()
                    }
                }
            }
        }
    }