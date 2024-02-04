package my.homework.service;

import lombok.RequiredArgsConstructor;
import my.homework.api.IssueRequest;
import my.homework.model.Issue;
import my.homework.repository.*;
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
    private final BookRepositoryInt bookRepository;
    private final ReaderRepositoryInt readerRepository;
    private final IssueRepositoryInt issueRepository;

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

    public Issue getIssue(Long id) {
        return issueRepository.getIssueById(id);
    }

    public boolean checkReaderInIssues(Long readerId) {
        List<Issue> issueList = issueRepository.getIssuesByReaderId(readerId).stream()
                .filter(it -> it.getReturned_at() == null)
                .toList();

        return issueList.size() < maxBook;
    }

    public Issue returnBook(long id) {
        Issue issue = issueRepository.getIssueById(id);
        issueRepository.getIssueById(id).setReturned_at(LocalDateTime.now());
        return issue;

    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }
}
