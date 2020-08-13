package com.ihadzhi.eatlimination.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ihadzhi.eatlimination.data.DietDao;
import com.ihadzhi.eatlimination.data.EatliminationDatabase;
import com.ihadzhi.eatlimination.data.FoodDao;
import com.ihadzhi.eatlimination.data.SymptomRecord;
import com.ihadzhi.eatlimination.databinding.RecordingsItemBinding;

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
    private FoodDao foodDao;
    private DietDao dietDao;

    public ViewRecordingsAdapter(Context context, RecordClickListener recordClickListener) {
        this.context = context;
        this.recordClickListener = recordClickListener;
        foodDao = EatliminationDatabase.getInstance(context).foodDao();
        dietDao = EatliminationDatabase.getInstance(context).dietDao();
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

        }

        @Override
        public void onClick(View v) {
            if (recordClickListener != null && records != null && records.size() > 0) {
                recordClickListener.execute(records.get(getAdapterPosition()));
            }
        }

    }
}
