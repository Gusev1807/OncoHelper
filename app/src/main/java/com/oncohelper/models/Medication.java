package com.oncohelper.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medications")
public class Medication {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String dosage;
    public String timesString; // "08:00,13:00,20:00"
    public boolean isActive = true;

    public Medication(String name, String dosage, String timesString) {
        this.name = name;
        this.dosage = dosage;
        this.timesString = timesString;
    }
}
