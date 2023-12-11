package com.example.demo.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.annotations.Where
import java.util.*

@Entity(name = "books")
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE books SET deleted = true WHERE id=?")
data class Book(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false)
        var id: Long? = null,

        @Column(nullable = false)
        var title: String? = null,

        @Column(nullable = false)
        var author: String? = null,

        @Column(unique = true, nullable = false)
        var isbn: String? = null,

        var available: Boolean = true,

        @CreationTimestamp
        @Column(nullable = false, updatable = false)
        var dateCreated: Date? = null,

        @UpdateTimestamp
        @Column(nullable = false)
        var dateUpdated: Date? = null,

        @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
        var deleted: Boolean = false

)