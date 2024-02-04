package my.homework.repository;

import my.homework.model.Issue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class IssueRepository {

    private final List<Issue> issues;

    public IssueRepository(List<Issue> issues) {
        this.issues = new ArrayList<>();
    }

    public void save(Issue issue) {
        issues.add(issue);
    }

    public Issue getIssueById(long id) {
        return issues.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);

    }

    public List<Issue> getReaderIssues(long readerId) {
        return issues.stream().filter(it -> Objects.equals(it.getReaderId(), readerId)).toList();
    }

    public List<Issue> getAllIssues() {
        return List.copyOf(issues);
    }

}
