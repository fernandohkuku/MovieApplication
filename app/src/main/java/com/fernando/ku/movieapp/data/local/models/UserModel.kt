package com.fernando.ku.movieapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class UserModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val uuid: String? = "N/A",
    val userName: String? = "N/A"
)
