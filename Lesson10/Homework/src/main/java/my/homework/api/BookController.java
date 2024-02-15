package my.homework.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.homework.aspect.Timer;
import my.homework.model.Book;
import my.homework.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Tag(name = "Book")
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService service;


    @Operation(summary = "get  book by id", description = "Загружает книгу по id")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Book book  = service.getBook(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "get all books", description = "Загружает все книги")
    @GetMapping
    public List<Book> getAll () {
        return service.getAllBooks();
    }

    @Operation(summary = "create book", description = "Создает книгу по id")
    @PostMapping()
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        return new ResponseEntity<>(service.addBook(book), HttpStatus.CREATED) ;
    }

    @Operation(summary = "delete book by id", description = "Удаляет книгу по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        service.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
