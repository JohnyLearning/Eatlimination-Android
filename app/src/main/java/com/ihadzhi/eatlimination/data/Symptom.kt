package com.ihadzhi.eatlimination.data

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
class Symptom() : Parcelable {
    @PrimaryKey
    var id: Long = 1
    var imageName: String? = null
    var name: String? = null
    var description: String? = null

    constructor(id: Long, name: String?, description: String?, imageName: String?): this() {
        this.id = id
        this.imageName = imageName
        this.name = name
        this.description = description
    }

    fun getImage(context: Context): Drawable? {
        return context.getDrawable(context.resources.getIdentifier(imageName, "drawable", context.packageName))
    }
}