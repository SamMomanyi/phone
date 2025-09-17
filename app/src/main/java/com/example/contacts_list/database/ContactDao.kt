package com.example.contacts_list.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

//indicate it is a DAO class
@Dao
interface  ContactDao {

    @Upsert
    suspend fun upsertContact(contact : ContactProperties)

    @Delete
    suspend fun deleteContact(contact : ContactProperties)

    @Query("SELECT * FROM ContactsTable ORDER BY firstName ASC")
    fun getContactsOrderedByFirstName() : Flow<List<ContactProperties>>

    @Query("SELECT * FROM ContactsTable ORDER BY lastName ASC")
    fun getContactsOrderedByLastName() : Flow<List<ContactProperties>>

    @Query("SELECT * FROM ContactsTable ORDER BY phoneNumber ASC")
    fun getContactsOrderedByPhoneNumber() : Flow<List<ContactProperties>>


}