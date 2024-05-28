package com.example.konustest.Service;

import com.example.konustest.Dto.AuthorDto;

import java.util.List;
import java.util.Map;

public interface AuthorService {

    AuthorDto addNewAuthor(AuthorDto authorDto);

    AuthorDto updateAuthor(Long id, Map<Object, Object> fields);

    void deleteAuthorAndHisBooksById(Long id);

    void deleteAuthorById(Long id);

    List<AuthorDto> getAllAuthor();
}
