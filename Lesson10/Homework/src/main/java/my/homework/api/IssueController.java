package my.homework.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import my.homework.model.Issue;
import my.homework.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@Tag(name = "Issue")
@RequestMapping("/api/issue")
public class IssueController {

    @Autowired
    private IssueService service;

    @Operation(summary = "get all issues", description = "Загружает все выдачи")
    @GetMapping
    public List<Issue> getAll () {
        return service.getAllIssues();
    }

    @Operation(summary = "get issue by id", description = "Загружает факт выдачи по id")
    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssue(@PathVariable long id) {
        Issue issue = service.getIssue(id);
        if (issue != null) {
            return new ResponseEntity<>(issue, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "create issue", description = "Создает факт выдачи")
    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
        System.out.println(request);

        final Issue issue;
        if (!service.checkReaderInIssues(request.getReaderId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        try {
            issue = service.issue(request);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(issue);

    }

    @Operation(summary = "set return date", description = "Устанавливает факт возврата книги")
    @PutMapping("/{id}")
    public Issue returnBook(@PathVariable long id) {
        return service.returnBook(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
        service.deleteIssue(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
