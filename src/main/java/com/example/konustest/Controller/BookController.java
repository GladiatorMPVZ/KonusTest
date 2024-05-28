package com.example.konustest.Controller;

import com.example.konustest.Dto.BookDto;
import com.example.konustest.Service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookServiceImpl bookService;

    @PostMapping("/add")
    public ResponseEntity<BookDto> addNewBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.addNewBook(bookDto));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id,
                                              @RequestBody Map<Object, Object> fields) {
        return ResponseEntity.ok(bookService.updateBookInformation(id, fields));
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam(required = false) String title,
                                                     @RequestParam(required = false) String isbn,
                                                     @RequestParam(required = false) String authorId) {
        return ResponseEntity.ok(bookService.getAllBooks(title, isbn, authorId));
    }
}
