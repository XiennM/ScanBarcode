package com.example.scanbarcode

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity
data class Product(@PrimaryKey val id: UUID = UUID.randomUUID()) {}