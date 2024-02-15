package my.homework.api;

import lombok.Data;
import my.homework.junit.JUnitSpringBootBase;
import my.homework.model.Book;
import my.homework.model.Issue;
import my.homework.model.Reader;
import my.homework.repository.BookRepositoryInt;
import my.homework.repository.IssueRepositoryInt;
import my.homework.repository.ReaderRepositoryInt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class IssueControllerTest extends JUnitSpringBootBase {


    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ReaderRepositoryInt readerRepository;
    @Autowired
    BookRepositoryInt bookRepository;
    @Autowired
    IssueRepositoryInt issueRepository;

    @Data
    static class JunitIssue {
        private Long id;
        private Long bookId;
        private Long readerId;
        private LocalDate issued_at;
    }

    @Test
    void testGetAll() {
        bookRepository.saveAll(List.of(
                Book.ofName("book1"),
                Book.ofName("book2")
        ));

        readerRepository.saveAll(List.of(
                Reader.ofName("reader1"),
                Reader.ofName("reader2")
        ));

        List<Issue> expected = issueRepository.saveAll(List.of(
                Issue.ofName(readerRepository.getReaderById(2L).getId(), bookRepository.getBookById(2L).getId()),
                Issue.ofName(readerRepository.getReaderById(3L).getId(), bookRepository.getBookById(3L).getId())
        ));


        List<JunitIssue> responseBody = webTestClient.get()
                .uri("/api/issue")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JunitIssue>>() {
                })
                .returnResult()
                .getResponseBody();
        System.out.println(responseBody);

        Assertions.assertEquals(expected.size(), responseBody.size());
        for (JunitIssue issueResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), issueResponse.getId()))
                    .filter(it -> Objects.equals(it.getBookId(), issueResponse.getBookId()))
                    .filter(it -> Objects.equals(it.getIssued_at(), issueResponse.getIssued_at()))
                    .anyMatch(it -> Objects.equals(it.getReaderId(), issueResponse.getReaderId()));
            Assertions.assertTrue(found);
        }
        readerRepository.deleteAll();
        bookRepository.deleteAll();
        issueRepository.deleteAll();


    }

    @Test
    void testFindByIdSuccess() {
        Reader expectedReader = readerRepository.save(Reader.ofName("readername"));
        Book expectedBook = bookRepository.save(Book.ofName("bookname"));

        Issue expected = issueRepository.save(Issue.ofName(expectedReader.getId(), expectedBook.getId()));

        System.out.println(expected);

       JunitIssue responseBody = webTestClient.get()
                .uri("/api/issue/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JunitIssue.class)
                .returnResult().getResponseBody();

        System.out.println(responseBody);

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getReaderId(), responseBody.getReaderId());
        Assertions.assertEquals(expected.getBookId(), responseBody.getBookId());
        Assertions.assertEquals(expected.getIssued_at(), responseBody.getIssued_at());

        readerRepository.deleteAll();
        bookRepository.deleteAll();
        issueRepository.deleteAll();
    }

    @Test
    void testFindByIdNotFound() {
        webTestClient.get()
                .uri("/api/issue/-1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testSaveSuccess() {
        bookRepository.save(Book.ofName("test name"));
        readerRepository.save(Reader.ofName("test reader"));
        Long bookId = bookRepository.findAll().stream().findFirst().get().getId();
        Long readerId = readerRepository.findAll().stream().findFirst().get().getId();
        Issue request = Issue.ofName(readerId, bookId);

        Issue responseBody = webTestClient.post()
                .uri("/api/issue")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Issue.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        Assertions.assertTrue(issueRepository.findById(responseBody.getId()).isPresent());
    }

    @Test
    void testDelete() {
        Issue issue = new Issue();
        issueRepository.save(issue);


        Long requestId = issueRepository.findAll().stream().findFirst().get().getId();
        System.out.println(requestId);

        webTestClient.delete()
                .uri("/api/issue/" + requestId)
                .exchange()
                .expectStatus().isNoContent();
        webTestClient.get()
                .uri("/api/issue/" + requestId)
                .exchange()
                .expectStatus().isNotFound();
    }




}