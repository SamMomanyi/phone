package com.example.contacts_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.contacts_list.database.ContactDatabase
import com.example.contacts_list.ui.theme.Contacts_listTheme
import com.example.contacts_list.ui.view.ContactScreen
import com.example.contacts_list.ui.view.HomePage
import com.example.contacts_list.ui.view.contactsList
import com.example.contacts_list.viewModel.ContactsViewModel

class MainActivity : ComponentActivity() {

    //intializing the room database
    private val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contacts.db"
        ).build()
    }

    private val viewModel by viewModels<ContactsViewModel>(
        //since our viewModel takes in  some parameters we need to create a factory
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ContactsViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Contacts_listTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   // HomePage(Modifier.padding(innerPadding))
                    //finally get our state
                    val state by viewModel.state.collectAsState()
                    ContactScreen(state = state , onEvent = viewModel::onEvent ,Modifier.padding((innerPadding)))
                }
                }
            }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Contacts_listTheme {
//        contactsList(viewModel = ContactsViewModel())
//    }
//}