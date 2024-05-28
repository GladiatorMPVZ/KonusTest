package com.example.konustest.Mapper;

import com.example.konustest.Dto.AuthorDto;
import com.example.konustest.Entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper<AuthorDto, Author> {
    @Override
    public AuthorDto entityToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();

        authorDto.setId(author.getId());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        authorDto.setBirthYear(author.getBirthYear());
        authorDto.setDeathYear(author.getDeathYear());
        return authorDto;
    }

    @Override
    public Author dtoToEntity(AuthorDto authorDto) {
        Author author = new Author();

        author.setId(authorDto.getId());
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setBirthYear(authorDto.getBirthYear());
        author.setDeathYear(authorDto.getDeathYear());
        return author;
    }
}
