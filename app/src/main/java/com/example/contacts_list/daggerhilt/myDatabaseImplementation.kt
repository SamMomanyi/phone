package com.example.contacts_list.daggerhilt

import com.example.contacts_list.daggerhilt.di.ApplicationContext
import com.example.contacts_list.database.ContactDao
import com.example.contacts_list.database.ContactDatabase
import com.example.contacts_list.database.ContactProperties
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class myDatabaseImplementation
    @Inject constructor(
        private val database : ContactDao,
        private val app : ApplicationContext
    ) :ContactDao {
        //we could then write the functions here

    override suspend fun upsertContact(contact: ContactProperties) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteContact(contact: ContactProperties) {
        TODO("Not yet implemented")
    }

    override fun getContactsOrderedByFirstName(): Flow<List<ContactProperties>> {
        TODO("Not yet implemented")
    }

    override fun getContactsOrderedByLastName(): Flow<List<ContactProperties>> {
        TODO("Not yet implemented")
    }

    override fun getContactsOrderedByPhoneNumber(): Flow<List<ContactProperties>> {
        TODO("Not yet implemented")
    }

}