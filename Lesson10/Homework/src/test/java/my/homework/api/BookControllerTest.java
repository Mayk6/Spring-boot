package my.homework.api;

import lombok.Data;
import my.homework.junit.JUnitSpringBootBase;
import my.homework.model.Book;
import my.homework.model.Reader;
import my.homework.repository.BookRepositoryInt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.core.ParameterizedTypeReference;


import java.util.List;
import java.util.Objects;


class BookControllerTest extends JUnitSpringBootBase {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    BookRepositoryInt bookRepository;

    @Data
    static class JunitBook {
        private Long id;
        private String name;
    }

    @Test
    void testFindByIdSuccess() {
        Book expected = bookRepository.save(Book.ofName("book1"));
        bookRepository.save(Book.ofName("book11"));

        System.out.println(expected);

        JunitBook responseBody = webTestClient.get()
                .uri("/api/book/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JunitBook.class)
                .returnResult().getResponseBody();

        System.out.println(responseBody);

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getName(), responseBody.getName());
    }

    @Test
    void testGetAll() {
        bookRepository.deleteAll();

        List<Book> expected = bookRepository.saveAll(List.of(
                Book.ofName("book1"),
                Book.ofName("book2")
        ));
        System.out.println(expected);

        List<JunitBook> responseBody = webTestClient.get()
                .uri("/api/book")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JunitBook>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(expected.size(), responseBody.size());
        for (JunitBook bookResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), bookResponse.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), bookResponse.getName()));
            Assertions.assertTrue(found);
        }


    }

    @Test
    void testFindByIdNotFound() {
        webTestClient.get()
                .uri("/api/book/-1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testSave() {
        Book request = new Book();
        request.setName("test book");

        Reader response = webTestClient.post()
                .uri("/api/book")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Reader.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertTrue(bookRepository.existsById(response.getId()));
    }

    @Test
    void testDelete() {
        Book book = new Book();
        book.setName("test name");
        bookRepository.save(book);

        Long requestId = bookRepository.findAll().stream().findFirst().get().getId();
        System.out.println(requestId);

        webTestClient.delete()
                .uri("/api/book/" + requestId)
                .exchange()
                .expectStatus().isNoContent();
        webTestClient.get()
                .uri("/api/book/" + requestId)
                .exchange()
                .expectStatus().isNotFound();
    }




}