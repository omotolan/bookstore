package com.example.demo.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class GeneralException(message: String) : Exception(message) {

    lateinit var status: HttpStatusCode

    constructor(message: String, status: HttpStatus) : this(message) {
        this.status = status
    }
}