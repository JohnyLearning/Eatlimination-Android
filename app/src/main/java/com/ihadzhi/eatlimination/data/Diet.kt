package com.ihadzhi.eatlimination.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Diet(val isActive: Boolean, val createdAt: Date, val startedOn: Date?) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @Ignore
    constructor(active: Boolean, startedOn: Date?) : this(active, Date(), startedOn)

}