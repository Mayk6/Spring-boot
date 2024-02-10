package my.homework.repository;

import my.homework.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {

    private final List<Book> books;

    public BookRepository(List<Book> books) {
        this.books = new ArrayList<>();
    }


    public Book getBookById(long id) {
        return books.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);

    }

    public void save(Book book) {
        books.add(book);
    }

    public void delete(long id) {
        Book book = getBookById(id);
        books.remove(book);
    }

    public List<Book> getAllBooks() {
        return List.copyOf(books);
    }


}
