package com.ab.exomind.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Aya Boussaadia on 21,February,2021
 */
@Parcelize
@Entity(tableName = "album")
data class Album(
    @field:PrimaryKey
    val id: Int,
    val userId: String,
    val title: String
) : Parcelable
