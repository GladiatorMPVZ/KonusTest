package com.example.konustest.Dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {

    private Long id;

    private String title;

    private String isbn;

    private Integer publicationYear;

    private GenreDto genreId;

    private Integer pages;

    private AuthorDto authorId;
}
