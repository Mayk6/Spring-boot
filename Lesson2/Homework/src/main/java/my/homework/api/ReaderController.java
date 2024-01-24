package my.homework.api;

import my.homework.model.Issue;
import my.homework.model.Reader;
import my.homework.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private ReaderService service;

    @GetMapping("/{id}")
    public Reader getReader(@PathVariable long id) {
        return service.getReader(id);
    }

    @PostMapping()
    public Reader addReader(@RequestBody Reader reader) {
        return service.addReader(reader);
    }

    @DeleteMapping("/{id}")
    public Reader deleteBook(@PathVariable long id) {
        return service.deleteReader(id);
    }

    @GetMapping("/{id}/issue")
    public List<Issue> getReaderIssues(@PathVariable long id) {
        return service.getReaderIssues(id);
    }

}
