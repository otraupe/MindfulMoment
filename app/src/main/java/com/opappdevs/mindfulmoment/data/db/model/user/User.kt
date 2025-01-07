package com.opappdevs.mindfulmoment.data.db.model.user

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.opappdevs.mindfulmoment.data.db.Converters
import java.util.*

@Entity(tableName = "user_table", indices = [Index(value = ["id", "name"], unique = true)])
@TypeConverters(Converters::class)
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,                    // id is auto-incremented (from max ever used value)
    val name: String,                   // id and name must be unique (see above)
    val createdDate: Date = Date(),     // track when entity was created...
    val updatedDate: Date = Date()      // and updated
)