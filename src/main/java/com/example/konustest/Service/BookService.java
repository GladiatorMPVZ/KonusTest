package com.example.konustest.Service;

import com.example.konustest.Dto.BookDto;

import java.util.List;
import java.util.Map;

public interface BookService {

    BookDto addNewBook(BookDto bookDto);

    BookDto updateBookInformation(Long id, Map<Object, Object> fields);

    void deleteBookById(Long id);

    List<BookDto> getAllBooks(String title, String isbn, String authorId);


}
