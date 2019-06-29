package com.example.arinspectexercise.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.arinspectexercise.model.Facts

@Database(entities = [Facts::class], version = 1)
abstract class FactsDatabase : RoomDatabase() {

    /**
     * Get facts DAO
     */
    abstract fun factsDao(): FactsDao
}