package com.ihadzhi.eatlimination.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ihadzhi.eatlimination.R;
import com.ihadzhi.eatlimination.data.Food;
import com.ihadzhi.eatlimination.data.Symptom;
import com.ihadzhi.eatlimination.databinding.FoodsIncludedItemBinding;
import com.ihadzhi.eatlimination.databinding.SymptomsItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.Executor;

class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.SymptomsViewHolder> {

    @FunctionalInterface
    public interface SymptomClickListener {
        void execute(Symptom symptom);
    }

    private final Context context;
    private List<Symptom> symptoms;
    private SymptomClickListener symptomClickListener;

    public SymptomsAdapter(Context context, SymptomClickListener symptomClickListener) {
        this.context = context;
        this.symptomClickListener = symptomClickListener;
    }

    @NonNull
    @Override
    public SymptomsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        SymptomsItemBinding binding = SymptomsItemBinding.inflate(layoutInflater, parent, false);
        return new SymptomsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SymptomsViewHolder holder, int position) {
        Symptom symptom = symptoms.get(position);
        holder.bind(symptom);
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return symptoms != null ? symptoms.size() : 0;
    }

    class SymptomsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        SymptomsItemBinding binding;
        private Executor executor;

        public SymptomsViewHolder(SymptomsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Symptom symptom) {
            binding.setSymptom(symptom);
            binding.symptomImage.setImageDrawable(context.getResources().getDrawable(symptom.getImageResource(), null));
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (symptomClickListener != null && symptoms != null && symptoms.size() > 0) {
                symptomClickListener.execute(symptoms.get(getAdapterPosition()));
            }
        }

    }
}
