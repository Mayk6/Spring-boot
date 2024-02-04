package my.homework.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import my.homework.model.Issue;
import my.homework.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@Tag(name = "Issue")
@RequestMapping("/issue")
public class IssueController {

    @Autowired
    private IssueService service;

    @Operation(summary = "get issue by id", description = "Загружает факт выдачи по id")
    @GetMapping("/{id}")
    public Issue getIssue(@PathVariable long id) {
        return service.getIssue(id);
    }

    @Operation(summary = "create issue", description = "Создает факт выдачи")
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

    @Operation(summary = "set return date", description = "Устанавливает факт возврата книги")
    @PutMapping("/{id}")
    public Issue returnBook(@PathVariable long id) {
        return service.returnBook(id);
    }
}
