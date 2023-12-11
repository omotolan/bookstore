package com.example.demo.dto

import java.util.*

class BookDto {

    var id: Long = 0

    lateinit var title: String

    lateinit var author: String

    lateinit var isbn: String

    var available: Boolean = false

    lateinit var dateCreated: Date

    lateinit var dateUpdated: Date
}
