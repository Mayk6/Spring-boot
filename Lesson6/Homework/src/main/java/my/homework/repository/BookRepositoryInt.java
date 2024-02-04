package my.homework.repository;

import my.homework.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepositoryInt extends JpaRepository<Book, Long> {
    Book getBookById(Long id);
}
