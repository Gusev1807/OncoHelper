package com.oncohelper.ui.medications;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.oncohelper.data.MedicationRepository;
import com.oncohelper.models.Medication;
import java.util.List;

public class MedicationViewModel extends AndroidViewModel {
    private MedicationRepository repository;
    private LiveData<List<Medication>> allMedications;

    public MedicationViewModel(Application application) {
        super(application);
        repository = new MedicationRepository(application);
        allMedications = repository.getAllMedications();
    }

    public void insert(Medication medication) {
        repository.insert(medication);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public LiveData<List<Medication>> getAllMedications() {
        return allMedications;
    }
}