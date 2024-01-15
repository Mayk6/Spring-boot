package ru.geekbrains.firstProject.Repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.firstProject.Models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class StudentRepository {

    private final List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
        students.add(new Student("Гриша", "Первая"));
        students.add(new Student("Ilia", "Вторая"));
        students.add(new Student("Сергей", "Вторая"));
        students.add(new Student("Илья", "Первая"));
        students.add(new Student("Вася", "Вторая"));
        students.add(new Student("Саша", "Первая"));
        students.add(new Student("Ilia", "Четвертая"));
    }

    public List<Student> getAllStudents() {
        return List.copyOf(students);
    }

    public Student getById(long id) {
        return students.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public List<Student> getStudentsByName(String name) {
        System.out.println(students.stream()
                .filter(it -> it.getName().contains(name))
                .toList());
        return students.stream()
                .filter(it -> it.getName().contains(name))
                .toList();
    }

    public List<Student> getStudentsByGroup(String groupName) {
        System.out.println(students.stream()
                .filter(it -> Objects.equals(it.getGroupName(), groupName))
                .toList());
        return students.stream()
                .filter(it -> Objects.equals(it.getGroupName(), groupName))
                .toList();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student deleteStudent(long id) {
        Student student = getById(id);
        if (students.contains(student) && student != null) {
            students.remove(student);
            return student;
        } else {
            return null;
        }
    }

}
