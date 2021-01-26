package com.ihadzhi.eatlimination.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ihadzhi.eatlimination.data.Symptom
import com.ihadzhi.eatlimination.databinding.SymptomsItemBinding
import com.ihadzhi.eatlimination.ui.SymptomsAdapter.SymptomsViewHolder
import java.util.concurrent.Executor

typealias SymptomClickListener = (Symptom) -> Unit
typealias NewRecordClickListener = (Symptom) -> Unit
internal class SymptomsAdapter(private val context: Context,
                               private val symptomClickListener: SymptomClickListener?,
                               private val newRecordClickListener: NewRecordClickListener?) : RecyclerView.Adapter<SymptomsViewHolder>() {

    private var symptoms: List<Symptom?>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomsViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = SymptomsItemBinding.inflate(layoutInflater, parent, false)
        return SymptomsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SymptomsViewHolder, position: Int) {
        val symptom = symptoms?.get(position)
        if (symptom != null) {
            holder.bind(symptom)
        }
    }

    fun setSymptoms(symptoms: List<Symptom?>?) {
        this.symptoms = symptoms
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return symptoms?.size ?: 0
    }

    internal inner class SymptomsViewHolder(var binding: SymptomsItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(symptom: Symptom) {
            binding.symptom = symptom
            binding.symptomImage.setImageDrawable(symptom.getImage(context))
            itemView.setOnClickListener(this)
            if (symptom !=  null) {
                binding.symptomRecordingAction.setOnClickListener {
                    newRecordClickListener?.invoke(symptom)
                }
            }
        }

        override fun onClick(v: View) {
            symptoms?.get(adapterPosition)?.let { symptom ->
                symptomClickListener?.invoke(symptom)
            }

        }
    }
}