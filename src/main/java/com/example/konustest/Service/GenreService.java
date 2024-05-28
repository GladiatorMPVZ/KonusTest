package com.example.konustest.Service;

import com.example.konustest.Dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> getAllGenre();

    void deleteGenreAndHisBooksById(Long id);
    void deleteGenreById(Long id);

    GenreDto addNewGenre(GenreDto genreDto);

}
