package my.homework.service;

import lombok.RequiredArgsConstructor;
import my.homework.api.IssueRequest;
import my.homework.model.Issue;
import my.homework.repository.BookRepository;
import my.homework.repository.IssueRepository;
import my.homework.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssueService {

    @Value("${application.max-allowed-books:1}")
    private long maxBook;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public Issue issue(IssueRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }

            Issue issue = new Issue(request.getBookId(), request.getReaderId());
            issueRepository.save(issue);
            return issue;

    }

    public Issue getIssue(long id) {
        if (issueRepository.getIssueById(id) != null) {
            return issueRepository.getIssueById(id);
        } else {
            return null;
        }
    }

    public boolean checkReaderInIssues(long readerId){
        List<Issue> issueList = issueRepository.getReaderIssues(readerId).stream()
                .filter(it -> it.getReturned_at() == null)
                .toList();

        return issueList.size() < maxBook;
    }

    public Issue returnBook(long id) {
        Issue issue = issueRepository.getIssueById(id);
        if (issue != null) {
            issueRepository.getIssueById(id).setReturned_at(LocalDateTime.now());
            return issue;

        } else {
            return null;
        }

    }

    public List<Issue> getAllIssues() {
        return issueRepository.getAllIssues();
    }
}
