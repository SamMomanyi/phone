package com.example.contacts_list.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contacts_list.AppUtil
import com.example.contacts_list.model.ContactProperties
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ContactsViewModel : ViewModel() {

    //to avoid change by the UI or another class

    private val _contactsList = MutableStateFlow<List<ContactProperties>>(emptyList())
    val contactList: StateFlow<List<ContactProperties>> = _contactsList
    init{
        getContacts()
    }
    private fun getContacts() {
        _contactsList.value = AppUtil.contactList
    }

}
