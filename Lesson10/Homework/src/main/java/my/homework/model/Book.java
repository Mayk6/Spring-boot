package my.homework.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Entity
@Data
@Table(name = "books")
@RequiredArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;


    public static Book ofName(String name) {
        Book book = new Book();
        book.setName(name);
        return book;
    }


}
