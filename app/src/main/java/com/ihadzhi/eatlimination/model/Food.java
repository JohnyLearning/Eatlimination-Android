package com.ihadzhi.eatlimination.model;

import android.media.Image;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity
public class Food {

    @SerializedName("poster_path")
    private Date createdAt;

    private String externalId;

    private Image image;

    private String imageUrl;

    private String title;

}
