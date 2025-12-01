package com.oncohelper.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.oncohelper.models.Medication;
import java.util.List;

@Dao
public interface MedicationDao {
    @Insert
    void insert(Medication medication);

    @Query("SELECT * FROM medications WHERE isActive = 1 ORDER BY id DESC")
    LiveData<List<Medication>> getAllActive();

    @Query("DELETE FROM medications WHERE id = :id")
    void delete(int id);

    @Query("UPDATE medications SET isActive = 0 WHERE id = :id")
    void deactivate(int id);
}
