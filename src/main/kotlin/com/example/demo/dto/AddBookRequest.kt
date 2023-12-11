package com.example.demo.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class AddBookRequest {

    @field:NotBlank(message = "Title must not be blank")
    @field:NotNull(message = "Title must not be null")
    lateinit var title: String

    @field:NotBlank(message = "Author must not be blank")
    @field:NotNull(message = "Author must not be null")
    lateinit var author: String

    @field:NotBlank(message = "ISBN must not be blank")
    @field:NotNull(message = "ISBN must not be null")
    lateinit var isbn: String

    var available: Boolean = true

}
