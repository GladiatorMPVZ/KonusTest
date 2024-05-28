package com.example.konustest.Controller;


import com.example.konustest.Dto.AuthorDto;
import com.example.konustest.Service.impl.AuthorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/author")
public class AuthorController {

    private final AuthorServiceImpl authorService;

    @PostMapping("/add")
    public ResponseEntity<AuthorDto> addNewAuthor(@RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.addNewAuthor(authorDto));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id,
                                                  @RequestBody Map<Object, Object> fields) {
        return ResponseEntity.ok(authorService.updateAuthor(id, fields));
    }

    @DeleteMapping("/all/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAuthorAndHisBooksById(@PathVariable Long id) {
        authorService.deleteAuthorAndHisBooksById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAuthorById(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorDto>> getAllAuthor() {
        return ResponseEntity.ok(authorService.getAllAuthor());
    }
}
