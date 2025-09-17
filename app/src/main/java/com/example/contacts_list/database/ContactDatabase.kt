package com.example.contacts_list.database

import android.provider.ContactsContract.CommonDataKinds.Contactables
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ContactProperties::class],
    version = 1,
    exportSchema = false
)
abstract class ContactDatabase : RoomDatabase() {
    abstract val dao: ContactDao
}