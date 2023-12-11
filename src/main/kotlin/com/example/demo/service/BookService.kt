package com.example.demo.service

import com.example.demo.dto.AddBookRequest
import com.example.demo.dto.BookDto
import com.example.demo.dto.UpdateBookRequest

interface BookService  {

    fun addBook(addBookRequest: AddBookRequest): BookDto

    fun getBooks(): List<BookDto>

    fun updateBook(bookId: Long, updatedBook: UpdateBookRequest): BookDto

    fun deleteBook(bookId: Long)
}