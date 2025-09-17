package com.example.contacts_list

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.NavigationBar
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.contacts_list.database.ContactProperties


object AppUtil {
   val contactList =  mutableListOf<ContactProperties>(
   )

    val deleteIcon : ImageVector = Icons.Default.Delete
   }

//limits the number of know instances in a class
enum class SortType {
    //different fields we can sort based on
    FIRST_NAME,
    LAST_NAME,
    PHONE_NUMBER
}

//mostly used to model api calls
//defines different condition that include their own data
sealed class DatabaseResponse<out Contacts>
data class Success(val data : List<ContactProperties>) : DatabaseResponse<List<ContactProperties>>()
data class Failure(val ex : Exception?) : DatabaseResponse<Nothing>()
data object isLoading : DatabaseResponse<Nothing>()

//a handy way to handle events on the UI e.g clicking the add button , whether to hide or show dialog
//defines a closed state of behaviours
sealed interface ContactEvent {

    object SaveContact : ContactEvent
    data class SetFirstName(val firstName : String): ContactEvent
    data class SetLastName(val lastName : String):ContactEvent
    data class SetPhoneNumber(val phoneNumber : String):ContactEvent
    object ShowDialog : ContactEvent
    object HideDialog : ContactEvent
    data class SortContacts(val sortType: SortType): ContactEvent
    data class DeleteContact(val contact: ContactProperties):ContactEvent

}


data class ContactState(
    val contacts: List<ContactProperties> = emptyList(),
    val firstName : String = "",
    val lastName: String = "",
    val phoneNumber : String = "",
    val isAddingContact: Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME
)



