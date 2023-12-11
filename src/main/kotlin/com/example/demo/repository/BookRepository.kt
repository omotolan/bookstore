package com.example.demo.repository

import com.example.demo.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {

    fun existsByIsbn(isbn: String?): Boolean
}