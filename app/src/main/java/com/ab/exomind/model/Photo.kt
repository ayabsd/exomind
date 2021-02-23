package com.ab.exomind.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Aya Boussaadia on 23,February,2021
 */

@Parcelize
@Entity(tableName = "photo")
data class Photo(
    @field:PrimaryKey
    val id: Int,
    val albumId: Int,
    val title: String,
    val url : String
) : Parcelable

