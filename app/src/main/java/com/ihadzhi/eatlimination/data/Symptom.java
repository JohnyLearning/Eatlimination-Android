package com.ihadzhi.eatlimination.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Symptom implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeInt(this.imageResource);
        dest.writeString(this.name);
        dest.writeString(this.description);
    }

    protected Symptom(Parcel in) {
        this.id = in.readLong();
        this.imageResource = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Symptom> CREATOR = new Parcelable.Creator<Symptom>() {
        @Override
        public Symptom createFromParcel(Parcel source) {
            return new Symptom(source);
        }

        @Override
        public Symptom[] newArray(int size) {
            return new Symptom[size];
        }
    };
}
