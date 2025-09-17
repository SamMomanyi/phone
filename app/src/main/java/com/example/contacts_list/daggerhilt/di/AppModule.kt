package com.example.contacts_list.daggerhilt.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contacts_list.database.ContactDao
import com.example.contacts_list.database.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(){
        return
        Room.databaseBuilder(
            context = ApplicationContext(),
            name = "contact_list",
            klass = ContactDatabase::class.java
        )
    }
}