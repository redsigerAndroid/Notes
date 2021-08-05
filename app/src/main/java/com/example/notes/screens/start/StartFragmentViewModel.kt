package com.example.notes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.notes.databse.firebase.AppFireBaseRepository
import com.example.notes.databse.room.AppRoomDatabase
import com.example.notes.databse.room.AppRoomRepository
import com.example.notes.utilits.REPOSITORY
import com.example.notes.utilits.TYPE_FIREBASE
import com.example.notes.utilits.TYPE_ROOM
import com.example.notes.utilits.showToast

class StartFragmentViewModel (application: Application) : AndroidViewModel(application) {
    private val mContext = application

    fun initDatabase(type:String, onSuccess:() -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }

            TYPE_FIREBASE -> {
                REPOSITORY = AppFireBaseRepository()
                REPOSITORY.connectToDatabase({onSuccess()}, { showToast(it)})
            }
        }
    }
}