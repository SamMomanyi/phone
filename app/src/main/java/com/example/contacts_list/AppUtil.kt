package com.example.contacts_list

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.NavigationBar
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.contacts_list.model.ContactProperties

object AppUtil {
   val contactList =  mutableListOf<ContactProperties>(
       ContactProperties("JohnBones", "0705687750"),
       ContactProperties("JohnBones", "0705687750"),
       ContactProperties("JohnBones", "0705687750"),
       ContactProperties("JohnBones", "0705687750"),
       ContactProperties("JohnBones", "0705687750"),
       ContactProperties("JohnBones", "0705687750")
   )

    val deleteIcon : ImageVector = Icons.Default.Delete
   }
