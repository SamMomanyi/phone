package com.example.contacts_list.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ContactsTable")
data class ContactProperties(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName : String,
    val lastName : String,
    val phoneNumber : String,

    )
