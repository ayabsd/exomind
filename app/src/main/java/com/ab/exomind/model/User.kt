package com.ab.exomind.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Aya Boussaadia on 19,February,2021
 */
@Parcelize
@Entity(tableName = "users")
data class User(
    @field:PrimaryKey

    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,

    ) : Parcelable
