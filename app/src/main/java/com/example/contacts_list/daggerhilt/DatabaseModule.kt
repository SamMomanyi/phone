package com.example.contacts_list.daggerhilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule  {
    @Binds
    @Singleton
    abstract  fun  bindMyDatabase(
        myDatabaseImplementation: myDatabaseImplementation
    ) : myDatabaseImplementation
}