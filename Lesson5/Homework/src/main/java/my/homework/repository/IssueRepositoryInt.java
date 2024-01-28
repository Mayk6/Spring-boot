package my.homework.repository;

import my.homework.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface IssueRepositoryInt extends JpaRepository<Issue, Long> {
    List<Issue> getIssuesByReaderId(Long readerId);
    Issue getIssueById(Long id);
}
