package com.example.arinspectexercise.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters

@Entity(tableName = "facts")
@TypeConverters(DataConverter::class)
data class Facts(
    @PrimaryKey val title: String,
    val rows: List<FactsItem>
)