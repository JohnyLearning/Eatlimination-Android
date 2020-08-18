package com.ihadzhi.eatlimination.data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
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

    public Drawable getImage(Context context) {
        return context.getDrawable(context.getResources().getIdentifier(name, "drawable", context.getPackageName()));
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
