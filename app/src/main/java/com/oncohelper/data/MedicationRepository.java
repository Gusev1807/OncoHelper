package com.oncohelper.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.oncohelper.models.Medication;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MedicationRepository {
    private MedicationDao medicationDao;
    private LiveData<List<Medication>> allMedications;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public MedicationRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        medicationDao = database.medicationDao();
        allMedications = medicationDao.getAllActive();
    }

    public void insert(Medication medication) {
        executor.execute(() -> {
            medicationDao.insert(medication);
        });
    }

    public void delete(int id) {
        executor.execute(() -> {
            medicationDao.delete(id);
        });
    }

    public LiveData<List<Medication>> getAllMedications() {
        return allMedications;
    }
}
