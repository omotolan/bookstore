package com.example.demo.controller

import com.example.demo.dto.AddBookRequest
import com.example.demo.dto.BookDto
import com.example.demo.dto.UpdateBookRequest
import com.example.demo.service.BookService
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequiredArgsConstructor
@RequestMapping("books")
@Validated
class BookController @Autowired constructor(private val bookService: BookService) {


    @PostMapping("add")
    fun createBook(@Valid @RequestBody requestDto: AddBookRequest): BookDto {
        return bookService.addBook(requestDto)
    }

    @PutMapping("/update/{bookId}")
    fun updateBook(@PathVariable bookId: Long, @Valid @RequestBody requestDto: UpdateBookRequest): BookDto {
        return bookService.updateBook(bookId, requestDto)
    }

    @GetMapping
    fun getBooks(): List<BookDto> {
        return bookService.getBooks()
    }

    @DeleteMapping("/delete/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteBook(@PathVariable bookId: Long) {
         bookService.deleteBook(bookId)
    }
}