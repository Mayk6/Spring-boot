package my.homework.service;

import lombok.RequiredArgsConstructor;
import my.homework.model.Book;
import my.homework.repository.BookRepository;
import my.homework.repository.BookRepositoryInt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepositoryInt bookRepository;

    public Book addBook(Book book) {
        if (book != null) {
            bookRepository.save(book);
            return book;
        } else {
            return null;
        }
    }

    public Book deleteBook(Long id) {
        Book book = bookRepository.getBookById(id);
        if (book != null) {
            bookRepository.deleteById(id);
            return book;
        } else {
            return null;
        }
    }

    public Book getBook(long id) {
        if (bookRepository.getBookById(id) != null) {
            return bookRepository.getBookById(id);
        } else {
            return null;
        }
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
