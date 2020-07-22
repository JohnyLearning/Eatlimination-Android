package com.ihadzhi.eatlimination.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class SymptomRecord {

    public enum SymptomCategory {
        green, yellow, red
    }



    @PrimaryKey
    @NonNull
    private long id;

    private SymptomCategory category;

    private String value;

    private Date timestamp;

    private long symptomId;

    public SymptomRecord(long id, SymptomCategory category, String value, Date timestamp, long symptomId) {
        this.id = id;
        this.category = category;
        this.value = value;
        this.timestamp = timestamp;
        this.symptomId = symptomId;
    }

    @Ignore
    public SymptomRecord(SymptomCategory category, String value, Date timestamp, long symptomId) {
        this.category = category;
        this.value = value;
        this.timestamp = timestamp;
        this.symptomId = symptomId;
    }

    @NonNull
    public long getId() {
        return id;
    }

    public SymptomCategory getCategory() {
        return category;
    }

    public String getValue() {
        return value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public long getSymptomId() {
        return symptomId;
    }
}
