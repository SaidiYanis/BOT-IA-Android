package com.supdevinci.aieaie.repository

import androidx.annotation.WorkerThread
import com.supdevinci.aieaie.dao.StupidDAO
import com.supdevinci.aieaie.entity.StupidSpeachEntity

class StupidSpeachRepository(private val stupidDAO: StupidDAO) {
    @WorkerThread
    fun getSpeach(idSpeach: Long) = stupidDAO.getSpeach(idSpeach)

    @WorkerThread
    suspend fun insertSpeach(StupidSpeachEntity: StupidSpeachEntity) = stupidDAO.insertSpeach(StupidSpeachEntity)
}