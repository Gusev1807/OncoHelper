package com.oncohelper.ui.medications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.oncohelper.R;
import com.oncohelper.models.Medication;
import java.util.ArrayList;
import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder> {
    private List<Medication> medications = new ArrayList<>();
    private OnDeleteClickListener deleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Medication medication);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteClickListener = listener;
    }

    @NonNull
    @Override
    public MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medication, parent, false);
        return new MedicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationViewHolder holder, int position) {
        Medication medication = medications.get(position);

        holder.textViewName.setText(medication.name);
        holder.textViewDosage.setText(medication.dosage);
        holder.textViewTimes.setText(medication.timesString);

        holder.buttonDelete.setOnClickListener(v -> {
            if (deleteClickListener != null) {
                deleteClickListener.onDeleteClick(medication);
            }
        });
    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
        notifyDataSetChanged();
    }

    static class MedicationViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewDosage, textViewTimes;
        Button buttonDelete;

        public MedicationViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDosage = itemView.findViewById(R.id.textViewDosage);
            textViewTimes = itemView.findViewById(R.id.textViewTimes);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
