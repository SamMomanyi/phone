package com.example.contacts_list.daggerhilt.viewModel

import androidx.compose.ui.graphics.Path.Companion.combine
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts_list.AppUtil
import com.example.contacts_list.ContactEvent
import com.example.contacts_list.ContactState
import com.example.contacts_list.SortType
import com.example.contacts_list.daggerhilt.myDatabase
import com.example.contacts_list.daggerhilt.myDatabaseImplementation
import com.example.contacts_list.database.ContactDao
import com.example.contacts_list.database.ContactProperties
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val dao : myDatabaseImplementation
) : ViewModel() {

    //to avoid change by the UI or another class

    private val _contactsList = MutableStateFlow<List<ContactProperties>>(emptyList())
    val contactList: StateFlow<List<ContactProperties>> = _contactsList
    
    private val _sortType = MutableStateFlow<SortType>(SortType.FIRST_NAME)

    //a flatmap uses some sort of reactive stream
    //  takes in a flow and returns a flow  when Ui is changed
    //whenever sort type changes switch and call the related dao function
    private val _contacts = _sortType
        .flatMapLatest { sortType ->
                when (sortType){
                    SortType.FIRST_NAME -> dao.getContactsOrderedByFirstName()
                    SortType.LAST_NAME -> dao.getContactsOrderedByLastName()
                    SortType.PHONE_NUMBER -> dao.getContactsOrderedByPhoneNumber()
                }
            //converts flow to a state flow
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _uiState = MutableStateFlow<ContactState>(ContactState())
    //with combine we can combine the three state flows into one state flow so that as soon as one of those codes emitts a value the .copy() code is executed,
    //so if there was a _sortType, we update the sortType
    val state = combine(_uiState,_sortType,_contacts){
            state, sortType, contacts ->
        state.copy(
            contacts = contacts,
            sortType = sortType,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())
    //whenever a user does some kind of event
    fun onEvent(event : ContactEvent) {
        when (event) {
            is ContactEvent.DeleteContact -> {
                viewModelScope.launch(Dispatchers.IO) {
                    dao.deleteContact(event.contact)
                }
            }

            ContactEvent.HideDialog -> {
                _uiState.update {
                    it.copy(
                        isAddingContact = false
                    )
                }
            }

            ContactEvent.SaveContact -> {
                val firstName = state.value.firstName
                val lastName = state.value.lastName
                val phoneNumber = state.value.phoneNumber

                if(firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()){
                    return
                }

                val contact = ContactProperties(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phoneNumber
                )
                viewModelScope.launch {
                    dao.upsertContact(contact )
                }
                _uiState.update {
                    it.copy(
                        //to hide dialogs
                        isAddingContact = false,
                        //reset our contacts to empty strings
                        firstName = "",
                        lastName = "",
                        phoneNumber = ""
                    )
                }
            }

            is ContactEvent.SetFirstName -> {
                _uiState.update {
                    it.copy(
                        firstName = event.firstName
                    )
                }
            }

            is ContactEvent.SetLastName -> {
                _uiState.update {
                    it.copy(
                        lastName = event.lastName
                    )
                }
            }

            is ContactEvent.SetPhoneNumber -> {
                _uiState.update {
                    it.copy(
                        phoneNumber = event.phoneNumber
                    )
                }
            }

            ContactEvent.ShowDialog -> {
                _uiState.update {
                    it.copy(
                        isAddingContact = true
                    )
                }
            }

            is ContactEvent.SortContacts -> {
                _sortType.value = event.sortType
            }
        }
    }

    init{
        getContacts()
    }
    private fun getContacts() {
        //start the loading
     _contactsList.value = AppUtil.contactList
    }
}
