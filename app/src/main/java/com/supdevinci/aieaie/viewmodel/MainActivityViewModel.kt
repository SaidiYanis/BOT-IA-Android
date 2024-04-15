package com.supdevinci.aieaie.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supdevinci.aieaie.dao.StupidDAO
import com.supdevinci.aieaie.db.StupidDB
import com.supdevinci.aieaie.entity.StupidEntity
import com.supdevinci.aieaie.repository.StupidRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val applicationContext: Context) : ViewModel() {
    private lateinit var database: StupidDB
    private lateinit var repository: StupidRepository

    val log = mutableStateListOf<StupidEntity>()

    init {
        viewModelScope.launch {
            database = StupidDB.getDatabase(applicationContext)
            repository = StupidRepository(database.stupidDAO())
        }
    }

    fun loadLog() {
        viewModelScope.launch(Dispatchers.IO) {
            log.clear()
            log.addAll(repository.getLog())
        }
    }

    fun insertLog(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertLog(StupidEntity(0, name))
            loadLog()
        }
    }

    fun deleteLog(idLog: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLog(idLog)
            repository.deleteSpeach(idLog)
            loadLog()
        }
    }

    fun deleteAllLog() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllLog()
            repository.deleteAllSpeach()
            loadLog()
        }
    }
}