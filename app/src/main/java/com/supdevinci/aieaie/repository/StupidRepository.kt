package com.supdevinci.aieaie.repository

import androidx.annotation.WorkerThread
import com.supdevinci.aieaie.dao.StupidDAO
import com.supdevinci.aieaie.entity.StupidEntity
import com.supdevinci.aieaie.viewmodel.StupidViewModel


class StupidRepository(private val stupidDAO: StupidDAO) {
    @WorkerThread
    fun getLog() = stupidDAO.getLog()

    @WorkerThread
    suspend fun deleteAllLog() = stupidDAO.deleteAllLog()

    @WorkerThread
    suspend fun insertLog(stupidEntity: StupidEntity) = stupidDAO.insertLog(stupidEntity)

    @WorkerThread
    fun getLog(idLog: Long) = stupidDAO.getLog(idLog)

    @WorkerThread
    suspend fun updateLogName(idLog: Long, name: String) = stupidDAO.updateLogName(idLog, name)

    @WorkerThread
    suspend fun deleteLog(idLog: Long) = stupidDAO.deleteLog(idLog)

    @WorkerThread
    suspend fun deleteSpeach(idLog: Long) = stupidDAO.deleteSpeach(idLog)

    @WorkerThread
    suspend fun deleteAllSpeach() = stupidDAO.deleteAllSpeach()
}