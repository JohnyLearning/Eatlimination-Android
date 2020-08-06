package com.ihadzhi.eatlimination.data;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Symptom {

    @PrimaryKey
    @NonNull
    private long id;

    @DrawableRes
    private int imageResource;

    private String name;

    private String description;

    public Symptom(@NonNull long id, String name, String description, int imageResource) {
        this.id = id;
        this.imageResource = imageResource;
        this.name = name;
        this.description = description;
    }

    @NonNull
    public long getId() {
        return id;
    }

    public @DrawableRes int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
