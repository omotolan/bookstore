package com.example.demo.exception

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)


    @ExceptionHandler
    fun handleGeneralException(ex: GeneralException): ResponseEntity<String> {
                log.info("An exception occurred [{}]", ex.message)

        return ResponseEntity(ex.message, ex.status)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errors = mutableMapOf<String, String>()

        ex.bindingResult.fieldErrors.forEach { fieldError: FieldError ->
            errors[fieldError.field] = fieldError.defaultMessage ?: "Validation error"
        }

        return ResponseEntity.badRequest().body(errors)
    }
}