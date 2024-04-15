package com.supdevinci.aieaie.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbLog")
data class StupidEntity(
    @PrimaryKey(autoGenerate = true)
    val idLog: Long,
    val name: String,
)