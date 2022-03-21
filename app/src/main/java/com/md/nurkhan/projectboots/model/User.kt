package com.md.nurkhan.projectboots.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val name2: Int,
    val name3: String,
    val name4: Int,
    val profilePhoto: Bitmap
    ): Parcelable
