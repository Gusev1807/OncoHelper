package com.oncohelper.ui.medications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.oncohelper.R;
import com.oncohelper.models.Medication;

public class AddMedicationFragment extends Fragment {
    private EditText editTextName, editTextDosage, editTextTimes;
    private Button buttonSave;
    private MedicationViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_medication, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        editTextDosage = view.findViewById(R.id.editTextDosage);
        editTextTimes = view.findViewById(R.id.editTextTimes);
        buttonSave = view.findViewById(R.id.buttonSave);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MedicationViewModel.class);

        buttonSave.setOnClickListener(v -> saveMedication());
    }

    private void saveMedication() {
        String name = editTextName.getText().toString().trim();
        String dosage = editTextDosage.getText().toString().trim();
        String times = editTextTimes.getText().toString().trim();

        if (name.isEmpty() || dosage.isEmpty()) {
            Toast.makeText(getContext(), "Заполните название и дозировку", Toast.LENGTH_SHORT).show();
            return;
        }

        if (times.isEmpty()) {
            times = "08:00"; // время по умолчанию
        }

        Medication medication = new Medication(name, dosage, times);
        viewModel.insert(medication);

        Toast.makeText(getContext(), "Лекарство добавлено", Toast.LENGTH_SHORT).show();

        // Очистить поля
        editTextName.setText("");
        editTextDosage.setText("");
        editTextTimes.setText("");
    }
}
