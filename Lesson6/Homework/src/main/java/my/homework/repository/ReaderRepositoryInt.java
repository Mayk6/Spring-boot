package my.homework.repository;

import my.homework.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReaderRepositoryInt extends JpaRepository<Reader, Long> {
    Reader getReaderById(Long id);
}
