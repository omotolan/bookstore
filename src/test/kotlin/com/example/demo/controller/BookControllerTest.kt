package com.example.demo.controller

import com.example.demo.dto.AddBookRequest
import com.example.demo.dto.UpdateBookRequest
import com.example.demo.exception.GeneralException
import com.example.demo.model.Book
import com.example.demo.repository.BookRepository
import com.example.demo.service.BookServiceImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*


class BookControllerTest {

    private val bookRepository: BookRepository = mockk(relaxUnitFun = true)

    private val bookService = BookServiceImpl(bookRepository)

    private val bookController = BookController(bookService)

    @Test
    fun createBook() {
        val addBookRequest = AddBookRequest()
        addBookRequest.author = " John doe"
        addBookRequest.title = "test"
        addBookRequest.isbn = "1234"
        val book = createBook(1, "John doe", "test", "1234", deleted = false)
        every { bookRepository.existsByIsbn(any()) } returns false
        every { bookRepository.save(any()) } returns book
        val result = bookController.createBook(addBookRequest)
        assertEquals("1234", result.isbn)
        verify(exactly = 1) { bookRepository.existsByIsbn(any()) }
        verify(exactly = 1) { bookRepository.save(any()) }

    }

    @Test
    fun createBookThrowsException() {
        val addBookRequest = AddBookRequest()
        addBookRequest.author = " John doe"
        addBookRequest.title = "test"
        addBookRequest.isbn = "1234"
        every { bookRepository.existsByIsbn(any()) } returns true
        assertThrows<GeneralException> {
            bookController.createBook(addBookRequest)

        }
        verify(exactly = 1) { bookRepository.existsByIsbn(any()) }
        verify(exactly = 0) { bookRepository.save(any()) }

    }


    @Test
    fun updateBook() {
        val book = createBook(1, "John doe", "test", "1234", deleted = false)
        val updateBookRequest = UpdateBookRequest()
        updateBookRequest.author = "none"
        updateBookRequest.title = "no return"
        updateBookRequest.available = true
        every { bookRepository.findById(any()) } returns Optional.of(book)
        every { bookRepository.save(any()) } returns book

        val result = bookController.updateBook(1, updateBookRequest)
        assertEquals(true, result.available)
        verify(exactly = 1) { bookRepository.findById(any()) }
        verify(exactly = 1) { bookRepository.save(any()) }
    }

    @Test
    fun getBooks() {
        val book = createBook(1, "John doe", "test", "1234", deleted = false)
        val books: List<Book> = listOf(book)
        every { bookRepository.findAll() } returns books
        val result = bookController.getBooks()
        assertEquals(1, result.size)

    }

    @Test
    fun deleteBook() {
        val book = createBook(1, "John doe", "test", "1234", deleted = false)
        every { bookRepository.findById(any()) } returns Optional.of(book)
        bookController.deleteBook(1)
        verify(exactly = 1) { bookRepository.delete(book) }

    }

    private fun createBook(id: Long, author: String, title: String, isbn: String, available: Boolean = true, deleted: Boolean = false): Book {
        return Book().apply {
            this.id = id
            this.author = author
            this.title = title
            this.isbn = isbn
            this.available = available
            this.deleted = deleted
            this.dateCreated = Date()
            this.dateUpdated = Date()
        }
    }
}