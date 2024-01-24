package my.homework.api;

import my.homework.model.Book;
import my.homework.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        return service.getBook(id);
    }

    @PostMapping()
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable long id) {
        return service.deleteBook(id);
    }

}
