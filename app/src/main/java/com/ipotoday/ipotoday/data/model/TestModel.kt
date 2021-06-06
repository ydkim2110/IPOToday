package com.ipotoday.ipotoday.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "test_model"
)
data class TestModel(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var title: String? = "",
    @ColumnInfo(name = "test_description") var testDescription: String? = ""
)