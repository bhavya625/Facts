package com.example.arinspectexercise.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.example.arinspectexercise.model.Facts

/**
 * Abstracts access to the facts database
 */
@Dao
interface FactsDao {

    /**
     * Insert facts into the database
     */
    @Insert(onConflict = REPLACE)
    fun insertFacts(facts: Facts)

    /**
     * Get all the facts from database
     */
    @Query("SELECT * FROM facts")
    fun getFacts(): LiveData<Facts>

    /**
     * Delete all the facts from database
     */
    @Query("DELETE FROM facts")
    fun deleteFacts()
}