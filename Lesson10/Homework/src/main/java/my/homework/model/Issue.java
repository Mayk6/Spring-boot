package my.homework.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bookId")
    private Long bookId;
    @Column(name = "readerId")
    private Long readerId;
    // Дата выдачи
    @Column(name = "issued_at")
    private LocalDate issued_at;
    @Column(name = "returned_at")
    private LocalDateTime returned_at;

    public Issue(Long bookId, Long readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.issued_at = LocalDateTime.now().toLocalDate();
        this.returned_at = null;
    }


    public void setReturned_at(LocalDateTime returned_at) {
        this.returned_at = returned_at;
    }

    public static Issue ofName(Long readerId, Long bookId) {
        return new Issue(bookId,readerId);
    }
}
