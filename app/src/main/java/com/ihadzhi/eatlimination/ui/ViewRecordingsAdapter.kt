package com.ihadzhi.eatlimination.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ihadzhi.eatlimination.R
import com.ihadzhi.eatlimination.data.SymptomRecord
import com.ihadzhi.eatlimination.data.SymptomRecord.SymptomCategory
import com.ihadzhi.eatlimination.databinding.RecordingsItemBinding
import com.ihadzhi.eatlimination.ui.ViewRecordingsAdapter.ViewRecordingsViewHolder
import org.joda.time.DateTime
import java.util.*
import java.util.concurrent.Executor

typealias RecordClickListener = (SymptomRecord) -> Unit

internal class ViewRecordingsAdapter(private val context: Context, private val recordClickListener: RecordClickListener?) : RecyclerView.Adapter<ViewRecordingsViewHolder>() {

    private var records: List<SymptomRecord>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewRecordingsViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = RecordingsItemBinding.inflate(layoutInflater, parent, false)
        return ViewRecordingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewRecordingsViewHolder, position: Int) {
        records?.get(position)?.let { record -> holder.bind(record) }
    }

    fun setRecords(records: List<SymptomRecord>?) {
        this.records = records
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return records?.size ?: 0
    }

    internal inner class ViewRecordingsViewHolder(var binding: RecordingsItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private val executor: Executor? = null
        fun bind(record: SymptomRecord) {
            binding.record = record
            itemView.setOnClickListener(this)
            val calendar = Calendar.getInstance()
            calendar.time = record.timestamp
            val dateTime = DateTime(calendar[Calendar.YEAR], calendar[Calendar.MONTH] + 1,
                    calendar[Calendar.DAY_OF_MONTH], calendar[Calendar.HOUR_OF_DAY], calendar[Calendar.MINUTE], calendar[Calendar.SECOND])
            val description = StringBuilder(context.getString(R.string.symptom_description,
                    record.value,
                    dateTime.toString("d MMM, yyyy @ HH:MM")))
            binding.symptomDescription.text = description
            if (record.category != null) {
                when (record.category) {
                    SymptomCategory.green -> binding.symptomCategory.setBackgroundResource(R.drawable.symptom_status_green_background)
                    SymptomCategory.yellow -> binding.symptomCategory.setBackgroundResource(R.drawable.symptom_status_yellow_background)
                    SymptomCategory.red -> binding.symptomCategory.setBackgroundResource(R.drawable.symptom_status_red_background)
                }
            }
        }

        override fun onClick(v: View) {
            records?.get(adapterPosition)?.let { record -> recordClickListener?.invoke(record) }
        }
    }
}