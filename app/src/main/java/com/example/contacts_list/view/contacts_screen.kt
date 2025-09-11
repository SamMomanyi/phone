package com.example.contacts_list.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.contacts_list.AppUtil
import com.example.contacts_list.model.ContactProperties
import com.example.contacts_list.viewModel.ContactsViewModel

@Composable
fun contactsList(viewModel: ContactsViewModel){

    val state by viewModel.uiState.collectAsState()
    val contacts by viewModel.contactList.collectAsState()




}

@Composable
fun Contact(contact : ContactProperties ){
    Card (
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
            contentColor = contentColorFor( MaterialTheme.colorScheme.surfaceVariant),
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 20.dp
        )

    ){
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        modifier = Modifier
                           // .size(10.dp)
                            .weight(0.2f)
                            .padding(
                                start = 13.5.dp
                            ),
                        text =  contact.phoneNumber,
                        style =  TextStyle(
                            color  = Color.LightGray,
                            fontWeight = FontWeight.Light
                        )
                        )
                    Text(
                        modifier = Modifier
                            .weight(0.8f)
                            .padding(
                                top = 8.dp,
                                start = 3.dp
                            )
                        ,
                        text = contact.name,
                        style =  TextStyle(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Right,
                            fontSize = 20.sp
                        )
                        )
                }
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = AppUtil.deleteIcon,
                        contentDescription = "Delete Button"
                    )
                }

            }
        }
    }
}