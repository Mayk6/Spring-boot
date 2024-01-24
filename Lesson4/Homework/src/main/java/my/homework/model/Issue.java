package my.homework.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Issue {
    public static long sequence = 1L;

    private final long id;
    private final long bookId;
    private final long readerId;
    // Дата выдачи
    private final LocalDateTime issued_at;
    private LocalDateTime returned_at;


    public Issue(long bookId, long readerId) {
        this.id = sequence++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.issued_at = LocalDateTime.now();
        this.returned_at = null;
    }

    public void setReturned_at(LocalDateTime returned_at) {
        this.returned_at = returned_at;
    }
}
