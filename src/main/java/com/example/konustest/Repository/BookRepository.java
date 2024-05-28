package com.example.konustest.Repository;

import com.example.konustest.Entity.Author;
import com.example.konustest.Entity.Book;
import com.example.konustest.Entity.Genre;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthorId(Author author);
    List<Book> findAllByGenreId(Genre genre);
}
