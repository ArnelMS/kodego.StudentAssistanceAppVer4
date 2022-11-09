package com.kodego.activity.app.studentassistanceappver4

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
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

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //DATA SOURCE
        var subjectsList = mutableListOf<Subjects>(
            Subjects(
                R.drawable.biologyicon,
                "Biology",
                "Study of living organisms",
                "M-W-F",
                "08:00 AM - 10:00 AM",
                "Prof. Aristotle"
            ),
            Subjects(
                R.drawable.chemistryicon,
                "Chemistry",
                "Scientific study of the properties and behavior of matter.",
                "M-W-F",
                "10:00 AM - 12:00 NN",
                "Prof. Antoine-Laurent de Lavoisier"
            ),
            Subjects(
                R.drawable.designicon,
                "Art",
                "Paintings, sculpture, and other pictures",
                "M-W-F",
                "01:PM PM - 03:00 PM",
                "Prof. Pablo Picasso"
            ),
            Subjects(
                R.drawable.englishicon,
                "English",
                "Study of literature, media and language",
                "M-W-F",
                "03:00 PM - 05:00 PM",
                "Prof. Geoffrey Chaucer"
            ),
            Subjects(
                R.drawable.historyicon,
                "History",
                " Study of past events",
                "T-TH-S",
                "08:00 AM - 10:00 AM",
                "Prof. Herodotus"
            ),
            Subjects(
                R.drawable.infotechicon,
                "I.T.",
                "Study cloud computing, cyber security, data management, networking, and more",
                "T-TH-S",
                "10:00 AM - 12:00 NN",
                "Prof. Claude Elwood Shannon"
            ),
            Subjects(
                R.drawable.mathicon,
                "Mathematics",
                "Study of abstract science of number, quantity, and space",
                "T-TH-S",
                "01:00 PM - 03:00 PM",
                "Prof. Archimedes"
            ),
            Subjects(
                R.drawable.scienceicon,
                "Science",
                "Study of  the structure and behaviour of the physical and natural world through observation and experiment",
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


        binding.imgProfilePhoto.setOnClickListener() {
            showCamera()
        }
        binding.imgCoverPhoto.setOnClickListener() {
            showCamera()
        }
    }
    private fun showGallery() {
        Dexter.withContext(this).withPermission(
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                val galleryIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryLauncher.launch(galleryIntent)
//                Toast.makeText(applicationContext, "Gallery Permission Granted", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(applicationContext, "Gallery Permission Denied", Toast.LENGTH_SHORT)
                    .show()
                gotoSettings()
            }

            override fun onPermissionRationaleShouldBeShown(
                request: PermissionRequest?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }

        }).onSameThread().check()
    }
    private fun showCamera() {
        Dexter.withContext(this).withPermission(
            Manifest.permission.CAMERA
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                startActivity(cameraIntent)
                cameraLauncher2.launch(cameraIntent)
                cameraLauncher.launch(cameraIntent)
                Toast.makeText(applicationContext, "Camera Permission Approved", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(applicationContext, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
//                gotoSettings()
            }

            override fun onPermissionRationaleShouldBeShown(
                request: PermissionRequest?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }

        }).onSameThread().check()
    }
    private fun gotoSettings() {
        AlertDialog.Builder(this).setMessage("It seems that your permission has been denied. Go to settings to enable permission")
            .setPositiveButton("Got to settings"){dialog, item ->
                val intent = Intent(Settings.ACTION_APPLICATION_SETTINGS)
                var uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }.setNegativeButton("Cancel"){dialog, item ->
                dialog.dismiss()
            }.show()
    }
    //handles images from the camera
    val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.extras.let {
                    val image: Bitmap = result.data?.extras?.get("data") as Bitmap
                    binding.imgProfilePhoto.setImageBitmap(image)

                }
            }
        }


    //handles images from gallery
    val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let {
                    val selectedImages = result.data?.data
                    binding.imgCoverPhoto.setImageURI(selectedImages)
                    binding.imgCoverPhoto.setImageURI(selectedImages)
                }

            }
        }
    val cameraLauncher2 =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.extras.let {
                    val image: Bitmap = result.data?.extras?.get("data") as Bitmap
                    binding.imgCoverPhoto.setImageBitmap(image)


                }
            }
        }
}