package com.example.notes.databse.firebase

import androidx.lifecycle.LiveData
import com.example.notes.models.AppNote
import com.example.notes.utilits.REF_DATABASE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllNotesLiveData : LiveData<List<AppNote>> () {

    private val listener = object : ValueEventListener {
        override fun onDataChange(p0: DataSnapshot) {
            value = p0.children.map {
                it.getValue(AppNote::class.java)?: AppNote()
            }
        }

        override fun onCancelled(p0: DatabaseError) {

        }
    }

    override fun onActive() {
        REF_DATABASE.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        REF_DATABASE.removeEventListener(listener)
        super.onInactive()
    }
}