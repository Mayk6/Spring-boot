package my.homework.repository;

import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    public void generateData() {
        books.addAll(List.of(
                new Book("Война и мир"),
                new Book("Мёртвые души"),
                new Book("Чистый код")
        ));
    }

    public Book getBookById(long id) {
        return books.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);

    }

    public void save(Book book){
        books.add(book);
    }

    public void delete(long id) {
        Book book = getBookById(id);
        books.remove(book);
    }



}
