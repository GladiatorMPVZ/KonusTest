package com.example.konustest.Service.impl;

import com.example.konustest.Dto.BookDto;
import com.example.konustest.Entity.Author;
import com.example.konustest.Entity.Book;
import com.example.konustest.Entity.Genre;
import com.example.konustest.Exception.ResourceNotFoundException;
import com.example.konustest.Mapper.BookMapper;
import com.example.konustest.Repository.AuthorRepository;
import com.example.konustest.Repository.BookRepository;
import com.example.konustest.Repository.GenreRepository;
import com.example.konustest.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;

    @Override
    public BookDto addNewBook(BookDto bookDto) {
        return bookMapper.entityToDto(bookRepository.save(bookMapper.dtoToEntity(bookDto)));
    }

    @Override
    public BookDto updateBookInformation(Long id, Map<Object, Object> fields) {
        Book oldOffer = bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("The specified book does not exist!"));

        fields.forEach((key, value) -> {
            if (key.equals("authorId")) {
                Map<String, Integer> authorMap = (Map<String, Integer>) value;
                Long authorId = Long.valueOf(authorMap.get("id"));
                Author author = authorRepository.findById(authorId).orElseThrow(() ->
                        new ResourceNotFoundException("The specified author does not exist!"));
                oldOffer.setAuthorId(author);
            } else {
                if (key.equals("genreId")) {
                    Map<String, Integer> genreMap = (Map<String, Integer>) value;
                    Long genreId = Long.valueOf(genreMap.get("id"));
                    Genre genre = genreRepository.findById(Long.valueOf(genreId)).orElseThrow(() ->
                            new ResourceNotFoundException("The specified genre does not exist!"));
                    oldOffer.setGenreId(genre);
                } else {
                    Field field = ReflectionUtils.findField(Book.class, (String) key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, oldOffer, value);
                }
            }});

        return bookMapper.entityToDto(bookRepository.save(oldOffer));
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> getAllBooks(String title, String isbn, String authorId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);

        List<Predicate> predicates = new ArrayList<>();

        if (title != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder
                    .lower(root.get("title")), "%" + title.toLowerCase() + "%"));
        }
        if (isbn != null) {
            predicates.add(criteriaBuilder.equal(root.get("isbn"), isbn));
        }
        if (authorId != null) {
            predicates.add(criteriaBuilder.equal(root.get("authorId").get("id"), authorId));
        }
        query.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Book> typedQuery = entityManager.createQuery(query);
        List<BookDto> resultList = typedQuery.getResultList().stream().map(bookMapper::entityToDto).toList();
        return resultList;
    }
}
