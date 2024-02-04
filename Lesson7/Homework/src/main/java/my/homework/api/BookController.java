package my.homework.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.homework.model.Book;
import my.homework.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Book")
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @Operation(summary = "get  book by id", description = "Загружает книгу по id")
    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        return service.getBook(id);
    }

    @Operation(summary = "create book", description = "Загружает книгу по id")
    @PostMapping()
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @Operation(summary = "delete book by id", description = "Удаляет книгу по id")
    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable long id) {
        return service.deleteBook(id);
    }

}
