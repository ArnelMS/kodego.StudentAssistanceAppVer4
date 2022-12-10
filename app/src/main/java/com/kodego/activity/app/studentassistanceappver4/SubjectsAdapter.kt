package com.kodego.activity.app.studentassistanceappver4

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kodego.activity.app.studentassistanceappver4.databinding.RowItemSubjectsBinding

data class SubjectsAdapter (val subjects:List<Subjects>):RecyclerView.Adapter<SubjectsAdapter.SubjectViewHolder>(){

    var onItemClick : ((Subjects)-> Unit)? = null

    inner class SubjectViewHolder(val binding:RowItemSubjectsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowItemSubjectsBinding.inflate(layoutInflater,parent, false)
        return SubjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.binding.apply {
            imgSubjectIcon.setImageResource(subjects[position].imageSubjectName)
            tvSubjectName.text = subjects[position].itemSubjectName
            tvSubjectSchedule.text = subjects[position].itemSubjectSchedule
            tvSubjectTime.text = subjects[position].itemSubjectTime
            tvProfessorName.text = subjects[position].itemSubjectProfessor
        }
        holder.itemView.setOnClickListener(){
            onItemClick?.invoke(subjects[position])
        }
    }

    override fun getItemCount(): Int {
        return subjects.size
    }
}