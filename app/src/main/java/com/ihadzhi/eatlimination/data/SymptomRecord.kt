package com.ihadzhi.eatlimination.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime
import java.util.*

@Entity
@Parcelize
class SymptomRecord : Parcelable {
    enum class SymptomCategory {
        green, yellow, red
    }

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
        private set
    var category: SymptomCategory?
        private set
    var value: String?
        private set
    var timestamp: Date?
        private set
    var symptomId: Long
        private set

    constructor(id: Long, category: SymptomCategory?, value: String?, timestamp: Date?, symptomId: Long) {
        this.id = id
        this.category = category
        this.value = value
        this.timestamp = timestamp
        this.symptomId = symptomId
    }

    @Ignore
    constructor(category: SymptomCategory?, value: String?, timestamp: Date?, symptomId: Long) {
        this.category = category
        this.value = value
        this.timestamp = timestamp
        this.symptomId = symptomId
    }

    @Ignore
    constructor(category: SymptomCategory?, value: String?, symptomId: Long) {
        this.category = category
        this.value = value
        timestamp = DateTime().toDate()
        this.symptomId = symptomId
    }
}