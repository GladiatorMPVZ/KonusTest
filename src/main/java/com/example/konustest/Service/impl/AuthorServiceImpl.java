package com.example.konustest.Service.impl;

import com.example.konustest.Dto.AuthorDto;
import com.example.konustest.Entity.Author;
import com.example.konustest.Entity.Book;
import com.example.konustest.Exception.CustomException;
import com.example.konustest.Exception.ResourceNotFoundException;
import com.example.konustest.Mapper.AuthorMapper;
import com.example.konustest.Repository.AuthorRepository;
import com.example.konustest.Repository.BookRepository;
import com.example.konustest.Service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorDto addNewAuthor(AuthorDto authorDto) {
        return authorMapper.entityToDto(authorRepository.save(authorMapper.dtoToEntity(authorDto)));
    }

    @Override
    public AuthorDto updateAuthor(Long id, Map<Object, Object> fields) {
        Author oldAuthor = authorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("The specified author does not exist!"));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Author.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, oldAuthor, value);
        });

        return authorMapper.entityToDto(authorRepository.save(oldAuthor));
    }

    @Override
    public void deleteAuthorAndHisBooksById(Long id) {
        authorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("The specified author does not exist!"));
        List<Book> bookList = bookRepository.findAllByAuthorId(new Author(id)).stream().toList();
        for (Book book : bookList) {
            bookRepository.deleteById(book.getId());
        }
        authorRepository.deleteById(id);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("The specified author does not exist!"));
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorDto> getAllAuthor() {
        return authorRepository.findAll().stream().map(authorMapper::entityToDto).toList();
    }
}
