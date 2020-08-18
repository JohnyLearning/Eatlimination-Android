package com.ihadzhi.eatlimination.ui;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ihadzhi.eatlimination.R;
import com.ihadzhi.eatlimination.data.DietDao;
import com.ihadzhi.eatlimination.data.EatliminationDatabase;
import com.ihadzhi.eatlimination.data.FoodDao;
import com.ihadzhi.eatlimination.data.SymptomRecord;
import com.ihadzhi.eatlimination.databinding.RecordingsItemBinding;

import org.joda.time.DateTime;

import java.util.List;
import java.util.concurrent.Executor;

class ViewRecordingsAdapter extends RecyclerView.Adapter<ViewRecordingsAdapter.ViewRecordingsViewHolder> {

    @FunctionalInterface
    public interface RecordClickListener {
        void execute(SymptomRecord record);
    }

    private final Context context;
    private List<SymptomRecord> records;
    private RecordClickListener recordClickListener;

    public ViewRecordingsAdapter(Context context, RecordClickListener recordClickListener) {
        this.context = context;
        this.recordClickListener = recordClickListener;
    }

    @NonNull
    @Override
    public ViewRecordingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        RecordingsItemBinding binding = RecordingsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewRecordingsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewRecordingsViewHolder holder, int position) {
        SymptomRecord record = records.get(position);
        holder.bind(record);
    }

    public void setRecords(List<SymptomRecord> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return records != null ? records.size() : 0;
    }

    class ViewRecordingsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecordingsItemBinding binding;
        private Executor executor;

        public ViewRecordingsViewHolder(RecordingsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(SymptomRecord record) {
            binding.setRecord(record);
            itemView.setOnClickListener(this);
            DateTime dateTime = new DateTime(record.getTimestamp());
            String time = dateTime.getHourOfDay() + ":" + dateTime.getMinuteOfHour();
            StringBuilder description =
                    new StringBuilder(context.getString(R.string.symptom_description,
                            record.getValue(),
                            dateTime.toString("d MMM, yyyy"),
                            time));
            binding.symptomDescription.setText(description);
            if (record.getCategory() != null) {
                switch (record.getCategory()) {
                    case green:
                        binding.symptomCategory.setBackgroundResource(R.drawable.symptom_status_green_background);
                        break;
                    case yellow:
                        binding.symptomCategory.setBackgroundResource(R.drawable.symptom_status_yellow_background);
                        break;
                    case red:
                        binding.symptomCategory.setBackgroundResource(R.drawable.symptom_status_red_background);
                        break;
                }
            }
        }

        @Override
        public void onClick(View v) {
            if (recordClickListener != null && records != null && records.size() > 0) {
                recordClickListener.execute(records.get(getAdapterPosition()));
            }
        }

    }
}
