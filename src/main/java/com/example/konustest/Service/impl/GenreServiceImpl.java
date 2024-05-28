package com.example.konustest.Service.impl;

import com.example.konustest.Dto.GenreDto;
import com.example.konustest.Entity.Book;
import com.example.konustest.Entity.Genre;
import com.example.konustest.Exception.ResourceNotFoundException;
import com.example.konustest.Mapper.GenreMapper;
import com.example.konustest.Repository.BookRepository;
import com.example.konustest.Repository.GenreRepository;
import com.example.konustest.Service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> getAllGenre() {
        return genreRepository.findAll().stream().map(genreMapper::entityToDto).toList();
    }

    @Override
    public void deleteGenreAndHisBooksById(Long id) {
        genreRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("The specified genre does not exist!"));
        List<Book> bookList = bookRepository.findAllByGenreId(new Genre(id)).stream().toList();
        for (Book book : bookList) {
            bookRepository.deleteById(book.getId());
        }
        genreRepository.deleteById(id);
    }

    @Override
    public void deleteGenreById(Long id) {
        genreRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("The specified genre does not exist!"));
        genreRepository.deleteById(id);
    }

    @Override
    public GenreDto addNewGenre(GenreDto genreDto) {
        return genreMapper.entityToDto(genreRepository.save(genreMapper.dtoToEntity(genreDto)));
    }
}
