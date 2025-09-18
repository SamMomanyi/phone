package com.example.contacts_list.daggerhilt.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contacts_list.database.ContactDao
import com.example.contacts_list.database.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Provides
    @Singleton
    fun provideMyDatabase(@ApplicationContext app:Context):ContactDatabase{
         return Room.databaseBuilder(
             context = app,
             klass = ContactDatabase::class.java,
             name = "contacts_list"
         ).build()
    }


}