package ru.geekbrains.firstProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.firstProject.Models.Student;
import ru.geekbrains.firstProject.Repositories.StudentRepository;

import java.util.List;

@RestController
public class StudentController {

    private StudentRepository repository;

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }


    @GetMapping(path = "/student")
    public List<Student> getStudents() {
        return repository.getAllStudents();
    }

    @GetMapping(path = "/student/{id}")
    public Student getStudent(@PathVariable long id) {
        System.out.println(repository.getStudentsByName("Ilia"));
        return repository.getById(id);
    }

    @GetMapping(path = "/student/search")
    public List<Student> getStudentsByName(@RequestParam String name) {
        return repository.getStudentsByName(name);
    }

    @GetMapping(path = "/group/{groupName}/student")
    public List<Student> getStudentsByGroup(@PathVariable String groupName) {
        return repository.getStudentsByGroup(groupName);
    }

    @PostMapping(path = "/student")
    public Student addStudent(@RequestBody Student newStudent) {
        repository.addStudent(newStudent);
        return newStudent;

    }

    @DeleteMapping(path = "/student/{id}")
    public Student deleteStudent(@PathVariable long id) {
        Student tempStudent = repository.deleteStudent(id);
        return tempStudent;
    }


}
