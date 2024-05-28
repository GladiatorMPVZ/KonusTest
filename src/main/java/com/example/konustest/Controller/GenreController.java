package com.example.konustest.Controller;

import com.example.konustest.Dto.GenreDto;
import com.example.konustest.Service.impl.GenreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/genre")
public class GenreController {

    private final GenreServiceImpl genreService;

    @GetMapping("/all")
    public ResponseEntity<List<GenreDto>> getAllGenre() {
        return ResponseEntity.ok(genreService.getAllGenre());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/all/{id}")
    public void deleteGenreAndHisBooksById(@PathVariable Long id) {
        genreService.deleteGenreAndHisBooksById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void deleteGenreById(@PathVariable Long id) {
        genreService.deleteGenreById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<GenreDto> addNewGenre(@RequestBody GenreDto genreDto) {
        return ResponseEntity.ok(genreService.addNewGenre(genreDto));
    }


}
