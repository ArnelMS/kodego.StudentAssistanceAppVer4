package com.kodego.activity.app.studentassistanceappver4

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.kodego.activity.app.studentassistanceappver4.databinding.ActivityHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var simpleDateFormat: SimpleDateFormat
    lateinit var date: String
    lateinit var textView: TextView
    lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgProfilePhoto.setOnClickListener() {
            showCamera()
        }
        binding.imgCoverPhoto.setOnClickListener() {
            showCamera()
        }

        //DATA SOURCE
        var subjectsList = mutableListOf<Subjects>(
            Subjects(
                R.drawable.biologyicon,
                "Biology",
                "M-W-F",
                "08:00 AM - 10:00 AM",
                "Prof. Aristotle"
            ),
            Subjects(
                R.drawable.chemistryicon,
                "Chemistry",
                "M-W-F",
                "10:00 AM - 12:00 NN",
                "Prof. Antoine-Laurent de Lavoisier"
            ),
            Subjects(
                R.drawable.designicon,
                "Art",
                "M-W-F",
                "01:PM PM - 03:00 PM",
                "Prof. Pablo Picasso"
            ),
            Subjects(
                R.drawable.englishicon,
                "English",
                "M-W-F",
                "03:00 PM - 05:00 PM",
                "Prof. Geoffrey Chaucer"
            ),
            Subjects(
                R.drawable.historyicon,
                "History",
                "T-TH-S",
                "08:00 AM - 10:00 AM",
                "Prof. Herodotus"
            ),
            Subjects(
                R.drawable.infotechicon,
                "I.T.",
                "T-TH-S",
                "10:00 AM - 12:00 NN",
                "Prof. Claude Elwood Shannon"
            ),
            Subjects(
                R.drawable.mathicon,
                "Mathematics",
                "T-TH-S",
                "01:00 PM - 03:00 PM",
                "Prof. Archimedes"
            ),
            Subjects(
                R.drawable.scienceicon,
                "Science",
                "T-TH-S",
                "03:00 PM - 05:00 PM",
                "Prof. Galileo Galilei"
            )
        )
        //PASS DATA SOURCE TO ADAPTER
        val adapter = SubjectsAdapter(subjectsList)
        adapter.onItemClick = {
            val intent = Intent(this, SubjectDetailActivity::class.java)
            intent.putExtra("imageSubjectName", it.imageSubjectName)
            intent.putExtra("itemSubjectName", it.itemSubjectName)
            intent.putExtra("itemSubjectSchedule", it.itemSubjectSchedule)
            intent.putExtra("itemSubjectTime", it.itemSubjectTime)
            intent.putExtra("itemSubjectProfessor", it.itemSubjectProfessor)

            Toast.makeText(applicationContext, it.itemSubjectName, Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        binding.recylerHome.adapter = adapter
        binding.recylerHome.layoutManager = LinearLayoutManager(this)

        textView = binding.tvCurrentDate
        calendar = Calendar.getInstance()
        simpleDateFormat = SimpleDateFormat("EEE | MMM dd, yyyy")
        date = simpleDateFormat.format(calendar.time)
        textView.text = date

    }


    private fun showCamera() {
        Dexter.withContext(this).withPermission(
            Manifest.permission.CAMERA
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                startActivity(cameraIntent) // only used to access camera

                cameraLauncher.launch(cameraIntent)
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionRationaleShouldBeShown(
                request: PermissionRequest?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }

        }).onSameThread().check()
    }

    val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.extras.let {
                    val image1: Bitmap = result.data?.extras?.get("data") as Bitmap
                    val image2: Bitmap = result.data?.extras?.get("data") as Bitmap
                    binding.imgProfilePhoto.setImageBitmap(image1)
                    binding.imgCoverPhoto.setImageBitmap(image2)

                }
            }
        }
}