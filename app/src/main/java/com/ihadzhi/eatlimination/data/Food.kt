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
class Food : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
        private set
    var createdAt: Date?
        private set
    var externalId: String?
        private set
    var imageUrl: String?
        private set
    var title: String?
        private set
    var dietId: Long

    constructor(id: Long, createdAt: Date?, externalId: String?, imageUrl: String?, title: String?, dietId: Long) {
        this.id = id
        this.createdAt = createdAt
        this.externalId = externalId
        this.imageUrl = imageUrl
        this.title = title
        this.dietId = dietId
    }

    @Ignore
    constructor(createdAt: Date?, externalId: String?, imageUrl: String?, title: String?, dietId: Long) {
        this.createdAt = createdAt
        this.externalId = externalId
        this.imageUrl = imageUrl
        this.title = title
        this.dietId = dietId
    }
}