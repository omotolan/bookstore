package com.example.demo.service

import com.example.demo.dto.AddBookRequest
import com.example.demo.dto.BookDto
import com.example.demo.dto.UpdateBookRequest
import com.example.demo.exception.GeneralException
import com.example.demo.exception.GlobalExceptionHandler
import com.example.demo.model.Book
import com.example.demo.repository.BookRepository
import com.example.demo.utils.copyPropertiesIgnoreNull
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class BookServiceImpl @Autowired constructor(private val bookRepository: BookRepository) : BookService {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    override fun addBook(addBookRequest: AddBookRequest): BookDto {
        if (bookRepository.existsByIsbn(addBookRequest.isbn)) {
            throw GeneralException("Book with ISBN ${addBookRequest.isbn} already exists.", HttpStatus.BAD_REQUEST)
        }
        val book = Book()
        BeanUtils.copyProperties(addBookRequest, book)
        val savedBook = bookRepository.save(book)

        log.info("book added [{}]", book)
        val bookDto = BookDto()
        BeanUtils.copyProperties(savedBook, bookDto)
        return bookDto
    }

    override fun getBooks(): List<BookDto> {

        val books = bookRepository.findAll()
        return books.map { book ->
            val bookDto = BookDto()
            BeanUtils.copyProperties(book, bookDto)
            bookDto
        }
    }

    override fun updateBook(bookId: Long, updateBook: UpdateBookRequest): BookDto {
        val existingBook = bookRepository.findById(bookId)
                .orElseThrow { GeneralException("Book not found with id: $bookId", HttpStatus.BAD_REQUEST) }
        copyPropertiesIgnoreNull(updateBook, existingBook)
        val updatedBook = bookRepository.save(existingBook)
        log.info("book updated [{}]", existingBook)
        val bookDto = BookDto()
        BeanUtils.copyProperties(updatedBook, bookDto)
        return bookDto
    }

    override fun deleteBook(bookId: Long) {
        val existingBook = bookRepository.findById(bookId)
                .orElseThrow { GeneralException("Book not found with id: $bookId", HttpStatus.BAD_REQUEST) }
        bookRepository.delete(existingBook)
        log.info("book deleted [{}]", existingBook)
    }

}