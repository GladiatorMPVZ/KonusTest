package com.example.konustest.Mapper;

import com.example.konustest.Dto.BookDto;
import com.example.konustest.Entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookDto, Book> {

    private final GenreMapper genreMapper = new GenreMapper();
    private final AuthorMapper authorMapper = new AuthorMapper();

    @Override
    public BookDto entityToDto(Book book) {
        BookDto bookDto = new BookDto();

        bookDto.setId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setPages(book.getPages());
        bookDto.setTitle(book.getTitle());
        bookDto.setPublicationYear(book.getPublicationYear());
        bookDto.setGenreId(genreMapper.entityToDto(book.getGenreId()));
        bookDto.setAuthorId(authorMapper.entityToDto(book.getAuthorId()));
        return bookDto;
    }

    @Override
    public Book dtoToEntity(BookDto bookDto) {
        Book book = new Book();

        book.setId(bookDto.getId());
        book.setIsbn(bookDto.getIsbn());
        book.setPages(bookDto.getPages());
        book.setTitle(bookDto.getTitle());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setGenreId(genreMapper.dtoToEntity(bookDto.getGenreId()));
        book.setAuthorId(authorMapper.dtoToEntity(bookDto.getAuthorId()));
        return book;
    }
}
