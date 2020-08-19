package com.ihadzhi.eatlimination.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.joda.time.DateTime;

import java.util.Date;

@Entity
public class SymptomRecord implements Parcelable {

    public enum SymptomCategory {
        green, yellow, red
    }

    @NonNull
    @PrimaryKey (autoGenerate = true)
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

    @Ignore
    public SymptomRecord(SymptomCategory category, String value, long symptomId) {
        this.category = category;
        this.value = value;
        this.timestamp = new DateTime().toDate();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeInt(this.category == null ? -1 : this.category.ordinal());
        dest.writeString(this.value);
        dest.writeLong(this.timestamp != null ? this.timestamp.getTime() : -1);
        dest.writeLong(this.symptomId);
    }

    protected SymptomRecord(Parcel in) {
        this.id = in.readLong();
        int tmpCategory = in.readInt();
        this.category = tmpCategory == -1 ? null : SymptomCategory.values()[tmpCategory];
        this.value = in.readString();
        long tmpTimestamp = in.readLong();
        this.timestamp = tmpTimestamp == -1 ? null : new Date(tmpTimestamp);
        this.symptomId = in.readLong();
    }

    public static final Parcelable.Creator<SymptomRecord> CREATOR = new Parcelable.Creator<SymptomRecord>() {
        @Override
        public SymptomRecord createFromParcel(Parcel source) {
            return new SymptomRecord(source);
        }

        @Override
        public SymptomRecord[] newArray(int size) {
            return new SymptomRecord[size];
        }
    };
}
