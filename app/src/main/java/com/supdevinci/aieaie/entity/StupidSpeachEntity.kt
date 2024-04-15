package com.supdevinci.aieaie.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbSpeach")
data class StupidSpeachEntity(
    @PrimaryKey(autoGenerate = true)
    val idSpeach: Long,
    val idLog: Long,
    val date: Long = System.currentTimeMillis(),
    val message: String,
)