package my.homework.api;

import lombok.Data;
import my.homework.junit.JUnitSpringBootBase;
import my.homework.model.Book;
import my.homework.model.Reader;
import my.homework.repository.ReaderRepositoryInt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.core.ParameterizedTypeReference;


import java.util.List;
import java.util.Objects;


class ReaderControllerTest extends JUnitSpringBootBase {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ReaderRepositoryInt readerRepository;

    @Data
    static class JunitReader {
        private Long id;
        private String name;
    }


    @Data
    static class TestReaderSave {
        private String name;

        public TestReaderSave(String name) {
            this.name = name;
        }
    }

    @BeforeEach
    void setUp() {
        readerRepository.deleteAll();
    }




    @Test
    void testFindByIdSuccess() {
        Reader expected = readerRepository.save(Reader.ofName("name1"));

        System.out.println(expected);

        JunitReader responseBody = webTestClient.get()
                .uri("/api/reader/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JunitReader.class)
                .returnResult().getResponseBody();

        System.out.println(responseBody);

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getName(), responseBody.getName());
    }

    @Test
    void testGetAll() {

        List<Reader> expected = readerRepository.saveAll(List.of(
                Reader.ofName("reader1"),
                Reader.ofName("reader2")
        ));
        System.out.println(expected);

        List<JunitReader> responseBody = webTestClient.get()
                .uri("/api/reader")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JunitReader>>() {
                })
                .returnResult()
                .getResponseBody();
        System.out.println(responseBody);

        Assertions.assertEquals(expected.size(), responseBody.size());
        for (JunitReader readerResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), readerResponse.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), readerResponse.getName()));
            Assertions.assertTrue(found);
        }


    }

    @Test
    void testFindByIdNotFound() {
        webTestClient.get()
                .uri("/api/reader/-1")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testSave() {
        TestReaderSave request = new TestReaderSave("test reader");

        Reader response = webTestClient.post()
                .uri("/api/reader")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Reader.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertTrue(readerRepository.existsById(response.getId()));
    }

    @Test
    void testDelete() {
        Reader reader = new Reader();
        reader.setName("test name");
        readerRepository.save(reader);

        Long requestId = readerRepository.findAll().stream().findFirst().get().getId();
        System.out.println(requestId);

        webTestClient.delete()
                .uri("/api/reader/" + requestId)
                .exchange()
                .expectStatus().isNoContent();
        webTestClient.get()
                .uri("/api/reader/" + requestId)
                .exchange()
                .expectStatus().isNotFound();
    }

}