package my.homework.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.homework.model.Issue;
import my.homework.model.Reader;
import my.homework.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Reader")
@RequestMapping("/api/reader")
public class ReaderController {

    @Autowired
    private ReaderService service;

    @Operation(summary = "get reader by id", description = "Загружает читателя по id")
    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReader(@PathVariable Long id) {
        Reader reader = service.getReader(id);
        if (reader != null) {
            return new ResponseEntity<>(reader, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "create reader", description = "Создает читателя")
    @PostMapping()
    public ResponseEntity<Reader> addReader(@RequestBody Reader reader) {
        return new ResponseEntity<>(service.addReader(reader), HttpStatus.CREATED);
    }

    @Operation(summary = "delete reader by id", description = "Удаляет читателя по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        service.deleteReader(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "get reader issues", description = "Загружает факты выдачи читателя по id")
    @GetMapping("/{id}/issue")
    public List<Issue> getReaderIssues(@PathVariable long id) {
        return service.getReaderIssues(id);
    }

    @Operation(summary = "get all readers", description = "Загружает всех читателей")
    @GetMapping
    public List<Reader> gelAll() {
        return service.getAllReaders();
    }


}
