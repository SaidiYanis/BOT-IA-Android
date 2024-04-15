package com.supdevinci.aieaie.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.supdevinci.aieaie.dao.StupidDAO
import com.supdevinci.aieaie.entity.StupidEntity
import com.supdevinci.aieaie.entity.StupidSpeachEntity

@Database(entities = [StupidEntity::class, StupidSpeachEntity::class], version = 1)
abstract class StupidDB : RoomDatabase() {
    abstract fun stupidDAO(): StupidDAO

    companion object {
        @Volatile
        private var INSTANCE: StupidDB? = null

        fun getDatabase(context: Context): StupidDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StupidDB::class.java,
                    "StupidDB"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}