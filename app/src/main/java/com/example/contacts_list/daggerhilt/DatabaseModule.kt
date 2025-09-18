package com.example.contacts_list.daggerhilt

import com.example.contacts_list.database.ContactDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
abstract class DatabaseModule(){
    @Binds
    @Singleton
    abstract fun bindDBImplementation(
        repository : myDatabaseImplementation
    ): ContactDao

}