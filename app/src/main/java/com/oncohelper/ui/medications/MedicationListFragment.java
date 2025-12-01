package com.oncohelper.ui.medications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oncohelper.R;
import com.oncohelper.models.Medication;
import java.util.List;

public class MedicationListFragment extends Fragment {
    private RecyclerView recyclerView;
    private MedicationAdapter adapter;
    private MedicationViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medication_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MedicationViewModel.class);

        setupRecyclerView();
        observeMedications();
    }

    private void setupRecyclerView() {
        adapter = new MedicationAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnDeleteClickListener(medication -> {
            viewModel.delete(medication.id);
            Toast.makeText(getContext(), "Лекарство удалено", Toast.LENGTH_SHORT).show();
        });
    }

    private void observeMedications() {
        viewModel.getAllMedications().observe(getViewLifecycleOwner(), medications -> {
            if (medications != null) {
                adapter.setMedications(medications);
            }
        });
    }
}
