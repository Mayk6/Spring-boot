package ru.gb.api;

import com.github.javafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.BookProvider;
import ru.gb.ReaderProvider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/issue")
public class IssueController {

    private final Faker faker;
    private final BookProvider bookProvider;
    private final ReaderProvider readerProvider;
    private final List<Issue> issues;


    @GetMapping
    public List<Issue> getAll(){
        return issues;
    }


    public IssueController(BookProvider bookProvider, ReaderProvider readerProvider) {
        this.faker = new Faker();
        this.bookProvider = bookProvider;
        this.readerProvider = readerProvider;
        this.issues = new ArrayList<>();
        refreshData();
    }


    private void refreshData() {
        issues.clear();
        for (int i = 0; i < 15; i++) {
            Issue issue = new Issue();
            issue.setId(UUID.randomUUID());
            issue.setIssuedAt(LocalDateTime.now());
            issue.setBook(bookProvider.getRandomBook());
            issue.setReader(readerProvider.getRandomReader());
            issues.add(issue);
        }
    }

    @GetMapping("/refresh")
    public List<Issue> refresh() {
        refreshData();
        return issues;
    }




}
