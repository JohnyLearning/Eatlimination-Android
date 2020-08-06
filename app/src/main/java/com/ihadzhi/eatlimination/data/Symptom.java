package com.ihadzhi.eatlimination.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Symptom {

    @PrimaryKey
    @NonNull
    private long id;

    private String imageName;

    private String name;

    private String description;

    public Symptom(@NonNull long id, String name, String description, String imageName) {
        this.id = id;
        this.imageName = imageName;
        this.name = name;
        this.description = description;
    }

    @NonNull
    public long getId() {
        return id;
    }

    public String getImageName() {
        return imageName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
