package com.ihadzhi.eatlimination.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
class Food() : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var createdAt: Date? = null
    var externalId: String? = null
    var imageUrl: String? = null
    var title: String? = null
    var dietId: Long = -1

    constructor(id: Long, createdAt: Date?, externalId: String?, imageUrl: String?, title: String?, dietId: Long) : this() {
        this.id = id
        this.createdAt = createdAt
        this.externalId = externalId
        this.imageUrl = imageUrl
        this.title = title
        this.dietId = dietId
    }

    @Ignore
    constructor(createdAt: Date?, externalId: String?, imageUrl: String?, title: String?, dietId: Long): this() {
        this.createdAt = createdAt
        this.externalId = externalId
        this.imageUrl = imageUrl
        this.title = title
        this.dietId = dietId
    }
}