package my.homework.repository;

import my.homework.model.Reader;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

    private final List<Reader> readers;

    public ReaderRepository(List<Reader> readers) {
        this.readers = new ArrayList<>();
    }

    public Reader getReaderById(long id) {
        return readers.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);

    }

    public void save(Reader reader) {
        readers.add(reader);
    }

    public void delete(long id) {
        Reader reader = getReaderById(id);
        readers.remove(reader);
    }

    public List<Reader> getAllReaders() {
        return List.copyOf(readers);
    }
}
