package com.example.notes.databse

import androidx.lifecycle.LiveData
import com.example.notes.models.AppNote

interface DatabaseRepository {
    val allnotes:LiveData<List<AppNote>>
    suspend fun insert(note: AppNote,onSuccess: () -> Unit)
    suspend fun delete(note: AppNote,onSuccess: () -> Unit)
}