package com.example.contacts_list.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Room
import com.example.contacts_list.database.ContactDatabase
import com.example.contacts_list.daggerhilt.viewModel.ContactsViewModel

@Composable
fun HomePage(padding: Modifier) {

    val bottomNavPaths = listOf<navItem>(
        navItem("Contacts", Icons.Default.ContactPhone),
        navItem("Favourites", Icons.Default.StarRate),
        navItem("Recent", Icons.Default.Schedule)
    )

    var selectedIndex by rememberSaveable() {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar() {
                bottomNavPaths.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = (index == selectedIndex),
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = navItem.title
                            )
                        },
                        label = { Text(text = navItem.title) }
                    )
                }
            }
        }
    ) {
        destination(modifier = Modifier.padding(it),selectedIndex)
    }
}

@Composable
fun destination(modifier: Modifier,selectedIndex: Int) {

    //intializing the room database

//    val viewModel = ContactsViewModel(
//        dao =
//    )
//
//    when (selectedIndex) {
//        0 -> { contactsList(viewModel = viewModel) }
//
//        1 -> { favouritesScreen() }
//
//        2 -> { recentScreen() }
//    }
}

data class navItem(
    val title: String,
    val icon: ImageVector
)