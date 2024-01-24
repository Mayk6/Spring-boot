package my.homework.service;

import lombok.RequiredArgsConstructor;
import my.homework.model.Book;
import my.homework.model.Issue;
import my.homework.model.Reader;
import my.homework.repository.IssueRepository;
import my.homework.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public Reader addReader(Reader reader) {
        if (reader != null) {
            readerRepository.save(reader);
            return reader;
        } else {
            return null;
        }
    }

    public Reader deleteReader(long id) {
        Reader reader = readerRepository.getReaderById(id);
        if (reader != null) {
            readerRepository.delete(id);
            return reader;
        } else {
            return null;
        }
    }

    public Reader getReader(long id) {
        if (readerRepository.getReaderById(id) != null) {
            return readerRepository.getReaderById(id);
        } else {
            return null;
        }
    }

    public List<Issue> getReaderIssues(long id) {
        return issueRepository.getReaderIssues(id);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.getAllReaders();
    }
}
