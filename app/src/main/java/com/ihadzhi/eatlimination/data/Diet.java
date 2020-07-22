package com.ihadzhi.eatlimination.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Diet {

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    private boolean active;

    private Date createdAt;

    private Date startedOn;

    public Diet(boolean active, Date createdAt, Date startedOn) {
        this.active = active;
        this.createdAt = createdAt;
        this.startedOn = startedOn;
    }

    @Ignore
    public Diet(boolean active, Date startedOn) {
        this(active, new Date(), startedOn);
    }

    @NonNull
    public long getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getStartedOn() {
        return startedOn;
    }
}
