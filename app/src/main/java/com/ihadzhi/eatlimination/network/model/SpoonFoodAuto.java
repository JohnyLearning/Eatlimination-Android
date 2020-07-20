package com.ihadzhi.eatlimination.network.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SpoonFoodAuto implements Parcelable {

    private int id;
    private String name;
    private String image;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.image);
    }

    public SpoonFoodAuto() {
    }

    protected SpoonFoodAuto(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<SpoonFoodAuto> CREATOR = new Parcelable.Creator<SpoonFoodAuto>() {
        @Override
        public SpoonFoodAuto createFromParcel(Parcel source) {
            return new SpoonFoodAuto(source);
        }

        @Override
        public SpoonFoodAuto[] newArray(int size) {
            return new SpoonFoodAuto[size];
        }
    };
}
