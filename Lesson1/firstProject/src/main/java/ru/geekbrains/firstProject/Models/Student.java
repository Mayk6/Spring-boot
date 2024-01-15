package ru.geekbrains.firstProject.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class Student {

    private static long idCounter = 1L;

    private final long id;
    private final String name;
    private String groupName;

    @JsonCreator
    public Student(String name, String groupName) {
        this.id = idCounter++;
        this.name = name;
        this.groupName = groupName;
    }

}
