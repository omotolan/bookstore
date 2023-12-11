package com.example.demo.dto

data class ExceptionResponse<T>(
        var respBody: T? = null,
        var respDescription: String? = null
)