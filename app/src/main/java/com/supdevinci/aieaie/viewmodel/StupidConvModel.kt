package com.supdevinci.aieaie.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.supdevinci.aieaie.db.StupidDB
import com.supdevinci.aieaie.entity.StupidEntity
import com.supdevinci.aieaie.entity.StupidSpeachEntity
import com.supdevinci.aieaie.repository.StupidRepository
import com.supdevinci.aieaie.repository.StupidSpeachRepository

class StupidViewModel(
    private val applicationContext: Context,
    var idLog: Long
) : ViewModel() {
    private lateinit var database: StupidDB
    private lateinit var stupidRepository: StupidRepository
    private lateinit var stupidSpeachRepository: StupidSpeachRepository

    val logEntity = mutableStateOf(StupidEntity(0, ""))
    val logSpeach = mutableStateListOf<StupidSpeachEntity>()

    var isFetching = mutableStateOf(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            database = StupidDB.getDatabase(applicationContext)
            stupidRepository = StupidRepository(database.stupidDAO())

            if (idLog == 0L) {
                idLog = stupidRepository.insertLog(StupidEntity(0, "Conversation"))
            }

            logEntity.value = stupidRepository.getLog(idLog)
            stupidSpeachRepository = StupidSpeachRepository(database.stupidDAO())
            loadSpeach()
        }
    }

    private fun loadSpeach() {
        viewModelScope.launch(Dispatchers.IO) {
            logSpeach.clear()
            logSpeach.addAll(stupidSpeachRepository.getSpeach(idLog))
        }
    }

    fun insertSpeach(speach: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newSpeach = StupidSpeachEntity(0, idLog, System.currentTimeMillis(), speach)
            val idSpeach = stupidSpeachRepository.insertSpeach(newSpeach)
            if (idSpeach > 0) {
                // Assuming idSpeach is the ID of the newly inserted speach
                loadSpeach() // Reload to include the new speach
            }
        }
    }

    fun updateLogName(newName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val updateCount = stupidRepository.updateLogName(idLog, newName)
            if (updateCount > 0) {
                // If at least one row was updated, we fetch the updated entity
                logEntity.value = stupidRepository.getLog(idLog)
            }
        }
    }

    fun deleteLog() {
        viewModelScope.launch(Dispatchers.IO) {
            stupidRepository.deleteLog(idLog)
            stupidRepository.deleteSpeach(idLog)
        }
    }
}
