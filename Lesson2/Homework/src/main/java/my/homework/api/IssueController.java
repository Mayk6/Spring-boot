package my.homework.api;


import lombok.extern.slf4j.Slf4j;
import my.homework.model.Issue;
import my.homework.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssueController {

    @Autowired
    private IssueService service;

    @GetMapping("/{id}")
    public Issue getIssue(@PathVariable long id) {
        return service.getIssue(id);
    }

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {

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

    @PutMapping("/{id}")
    public Issue returnBook(@PathVariable long id) {
        return service.returnBook(id);
    }
}
