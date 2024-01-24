package my.homework.service;

import lombok.RequiredArgsConstructor;
import my.homework.model.Book;
import my.homework.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book addBook(Book book) {
        if (book != null) {
            bookRepository.save(book);
            return book;
        } else {
            return null;
        }
    }

    public Book deleteBook(long id) {
        Book book = bookRepository.getBookById(id);
        if (book != null) {
            bookRepository.delete(id);
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
}
