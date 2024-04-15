package com.supdevinci.aieaie.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.supdevinci.aieaie.entity.StupidEntity
import com.supdevinci.aieaie.entity.StupidSpeachEntity

@Dao
interface StupidDAO {
    // Sélection de toutes les entités de conversation
    @Query("SELECT * FROM tbLog ORDER BY idLog DESC")
    fun getLog(): List<StupidEntity>

    // Suppression de toutes les conversations
    @Query("DELETE FROM tbLog")
    suspend fun deleteAllLog(): Int

    // Insertion d'une nouvelle conversation avec remplacement en cas de conflit
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(stupidEntity: StupidEntity): Long

    // Sélection d'une conversation par ID
    @Query("SELECT * FROM tbLog WHERE idLog = :idLog")
    fun getLog(idLog: Long): StupidEntity

    // Mise à jour du nom d'une conversation par ID
    @Query("UPDATE tbLog SET name = :name WHERE idLog = :idLog")
    suspend fun updateLogName(idLog: Long, name: String): Int

    // Suppression d'une conversation par ID
    @Query("DELETE FROM tbLog WHERE idLog = :idLog")
    suspend fun deleteLog(idLog: Long): Int

    // Sélection de tous les messages d'une conversation par ID
    @Query("SELECT * FROM tbSpeach WHERE idLog = :idLog ORDER BY idSpeach")
    fun getSpeach(idLog: Long): List<StupidSpeachEntity>

    // Insertion d'un nouveau message avec remplacement en cas de conflit
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpeach(stupidSpeachEntity: StupidSpeachEntity): Long

    // Suppression de tous les messages d'une conversation par ID
    @Query("DELETE FROM tbSpeach WHERE idLog = :idLog")
    suspend fun deleteSpeach(idLog: Long): Int

    // Suppression de tous les messages
    @Query("DELETE FROM tbSpeach")
    suspend fun deleteAllSpeach(): Int
}