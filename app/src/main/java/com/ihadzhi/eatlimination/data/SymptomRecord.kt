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
class SymptomRecord() : Parcelable {
    enum class SymptomCategory {
        green, yellow, red, unknown
    }

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var category: SymptomCategory? = SymptomCategory.unknown
    var value: String? = null
    var timestamp: Date? = null
    var symptomId: Long = -1

    constructor(id: Long, category: SymptomCategory?, value: String?, timestamp: Date?, symptomId: Long): this() {
        this.id = id
        this.category = category
        this.value = value
        this.timestamp = timestamp
        this.symptomId = symptomId
    }

    @Ignore
    constructor(category: SymptomCategory?, value: String?, timestamp: Date?, symptomId: Long): this() {
        this.category = category
        this.value = value
        this.timestamp = timestamp
        this.symptomId = symptomId
    }

    @Ignore
    constructor(category: SymptomCategory?, value: String?, symptomId: Long): this() {
        this.category = category
        this.value = value
        timestamp = DateTime().toDate()
        this.symptomId = symptomId
    }
}