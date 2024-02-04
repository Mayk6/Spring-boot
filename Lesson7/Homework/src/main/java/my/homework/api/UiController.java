package my.homework.api;

import my.homework.service.BookService;
import my.homework.service.IssueService;
import my.homework.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class UiController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private IssueService issueService;

    @GetMapping("/books")
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @GetMapping("/reader")
    public String showAllReaders(Model model) {
        model.addAttribute("readers", readerService.getAllReaders());
        return "readers";
    }

    @GetMapping("/issues")
    public String showAllIssues(Model model) {
        model.addAttribute("issues",issueService.getAllIssues());
        return "issues";

    }

    @GetMapping("/reader/{id}")
    public String showReaderIssues(@PathVariable long id, Model model) {

        model.addAttribute("issues", readerService.getReaderIssues(id));
        model.addAttribute("reader", readerService.getReader(id));

        return "readerIssues";

    }


}
