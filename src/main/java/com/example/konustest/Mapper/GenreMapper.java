package com.example.konustest.Mapper;

import com.example.konustest.Dto.GenreDto;
import com.example.konustest.Entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements Mapper<GenreDto, Genre> {
    @Override
    public GenreDto entityToDto(Genre genre) {
        GenreDto genreDto = new GenreDto();

        genreDto.setId(genre.getId());
        genreDto.setTitle(genre.getTitle());
        return genreDto;
    }

    @Override
    public Genre dtoToEntity(GenreDto genreDto) {
        Genre genre = new Genre();

        genre.setId(genreDto.getId());
        genre.setTitle(genreDto.getTitle());
        return genre;
    }
}
