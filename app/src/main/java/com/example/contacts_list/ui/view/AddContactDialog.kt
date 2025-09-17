package com.example.contacts_list.ui.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.contacts_list.ContactEvent
import com.example.contacts_list.ContactState


@Composable
fun AddContactDialog(
    state: ContactState,
    //the on Event is a lambda returns a unit
    onEvent : (ContactEvent) -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(ContactEvent.HideDialog)
        },
        title = { Text(text = "Add Contact") },
        text = {
            Column(
                //space between each Textfield
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.firstName,
                    onValueChange = {
                        onEvent(ContactEvent.SetFirstName(it))
                    },
                    placeholder = { Text(text = "First name") }
                )
                TextField(
                    value = state.lastName,
                    onValueChange = {
                        onEvent(ContactEvent.SetLastName(it))
                    },
                    placeholder = { Text(text = "Last Name") }
                )
                TextField(
                    value = state.phoneNumber,
                    onValueChange = {
                        onEvent(ContactEvent.SetPhoneNumber(it))
                    },
                    placeholder = { Text(text = "phone number") }

                )
            }
        },
        //buttons to confirm and delete dialog
                confirmButton = {
                    Button(onClick = {
                        onEvent(ContactEvent.SaveContact)
                    }) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        onEvent(ContactEvent.HideDialog)
                    }) {
                        Text("Cancel")
                    }
                }
    )
}

