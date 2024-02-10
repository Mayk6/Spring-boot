package my.homework.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.homework.model.Issue;
import my.homework.model.Reader;
import my.homework.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Reader")
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private ReaderService service;

    @Operation(summary = "get reader by id", description = "Загружает читателя по id")
    @GetMapping("/{id}")
    public Reader getReader(@PathVariable long id) {
        return service.getReader(id);
    }

    @Operation(summary = "create reader", description = "Создает читателя")
    @PostMapping()
    public Reader addReader(@RequestBody Reader reader) {
        return service.addReader(reader);
    }

    @Operation(summary = "delete reader by id", description = "Удаляет читателя по id")
    @DeleteMapping("/{id}")
    public Reader deleteBook(@PathVariable long id) {
        return service.deleteReader(id);
    }

    @Operation(summary = "get reader issues", description = "Загружает факты выдачи читателя по id")
    @GetMapping("/{id}/issue")
    public List<Issue> getReaderIssues(@PathVariable long id) {
        return service.getReaderIssues(id);
    }


}
