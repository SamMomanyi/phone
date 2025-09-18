package com.example.contacts_list.daggerhilt


import com.example.contacts_list.database.ContactDao
import com.example.contacts_list.database.ContactDatabase
import com.example.contacts_list.database.ContactProperties
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class myDatabaseImplementation @Inject constructor(
    private val database: ContactDatabase //no need to provide application context if we injecting database, it already has an application context
):ContactDao{

    private val dao = database.dao

    override suspend fun upsertContact(contact: ContactProperties) = dao.upsertContact(contact)

    override suspend fun deleteContact(contact: ContactProperties) = dao.deleteContact(contact)

    override fun getContactsOrderedByFirstName(): Flow<List<ContactProperties>> = dao.getContactsOrderedByFirstName()

    override fun getContactsOrderedByLastName(): Flow<List<ContactProperties>> = dao.getContactsOrderedByLastName()

    override fun getContactsOrderedByPhoneNumber(): Flow<List<ContactProperties>> = dao.getContactsOrderedByPhoneNumber()

}