package com.example.contacts_list.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contacts_list.AppUtil
import com.example.contacts_list.ContactEvent
import com.example.contacts_list.ContactState
import com.example.contacts_list.SortType
import com.example.contacts_list.database.ContactProperties
import com.example.contacts_list.viewModel.ContactsViewModel

@Composable
fun contactsList(viewModel: ContactsViewModel) {

    val state by viewModel.state.collectAsState()
    val contacts by viewModel.contactList.collectAsState()
}

@Composable
fun Contact(contact: ContactProperties,onEvent: (ContactEvent) -> Unit) {
    Card(
        modifier = Modifier
            .padding(
                start = 10.dp,
                top = 3.dp,
                end = 10.dp
            )
            .fillMaxWidth()
            .height(65.dp),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(
            contentColor = contentColorFor(MaterialTheme.colorScheme.surfaceVariant),
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 20.dp
        )

    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            // .size(10.dp)
                            .weight(0.2f)
                            .padding(
                                start = 13.5.dp
                            ),
                        text = contact.phoneNumber,
                        style = TextStyle(
                            color = Color.LightGray,
                            fontWeight = FontWeight.Light
                        )
                    )
                    Text(
                        modifier = Modifier
                            .weight(0.8f)
                            .padding(
                                top = 8.dp,
                                start = 3.dp
                            ),
                        text = contact.firstName,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Right,
                            fontSize = 20.sp
                        )
                    )
                }
                IconButton(onClick = {onEvent(ContactEvent.DeleteContact(contact)) }) {
                    Icon(
                        imageVector = AppUtil.deleteIcon,
                        contentDescription = "Delete Button"
                    )
                }

            }
        }
    }
}

@Composable
fun ContactScreen(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    padding : Modifier
) {
    //scaffold allows addition of a floating button
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(ContactEvent.ShowDialog)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add contact"
                )
            }
        },
        modifier = Modifier.padding(16.dp)
    ) { padding ->
        if(state.isAddingContact){
            AddContactDialog(state, onEvent )
        }

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //top row
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    SortType.values().forEach { sortType ->
                        //row for select icon
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onEvent(ContactEvent.SortContacts(sortType))
                                },
                            verticalAlignment = CenterVertically
                        ) {
                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = {
                                    onEvent(ContactEvent.SortContacts(sortType))
                                }
                            )
                            //display the name for ENUM CLass
                            Text(
                                text = sortType.name
                            )
                        }
                    }
                }
            }
            //second part of the lazy column
            items(state.contacts) { contact ->

                Contact(contact = contact,onEvent)
            }
        }
    }
}