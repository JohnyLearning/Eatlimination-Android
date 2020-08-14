package com.ihadzhi.eatlimination.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity
public class Food implements Parcelable {

    @NonNull
    @PrimaryKey (autoGenerate = true)
    private long id;

    private Date createdAt;

    private String externalId;

//    @SerializedName("imageData")
//    private Image imageData;

    private String imageUrl;

    private String title;

    public long getDietId() {
        return dietId;
    }

    public void setDietId(long dietId) {
        this.dietId = dietId;
    }

    private long dietId;

    public Food(long id, Date createdAt, String externalId, String imageUrl, String title, long dietId) {
        this.id = id;
        this.createdAt = createdAt;
        this.externalId = externalId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.dietId = dietId;
    }

    @Ignore
    public Food(Date createdAt, String externalId, String imageUrl, String title, long dietId) {
        this.createdAt = createdAt;
        this.externalId = externalId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.dietId = dietId;
    }


    @NonNull
    public long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
        dest.writeString(this.externalId);
        dest.writeString(this.imageUrl);
        dest.writeString(this.title);
        dest.writeLong(this.dietId);
    }

    protected Food(Parcel in) {
        this.id = in.readLong();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        this.externalId = in.readString();
        this.imageUrl = in.readString();
        this.title = in.readString();
        this.dietId = in.readLong();
    }

    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel source) {
            return new Food(source);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
}
