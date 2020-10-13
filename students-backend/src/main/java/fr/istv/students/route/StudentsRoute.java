package fr.istv.students.route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.istv.students.exception.ResourceNotFoundException;
import fr.istv.students.models.CreateStudent;
import fr.istv.students.models.Student;
import fr.istv.students.repositories.StudentsRepository;

@RestController
public class StudentsRoute {

    @Autowired
    StudentsRepository studentsRepository;

    @GetMapping("students/{id}")
    public Student getStudent(@PathVariable("id") Integer id) {
        return studentsRepository.findById(id).orElse(null);
    }

    @GetMapping("students")
    public List<Student> getAllStudents() {
        return studentsRepository.findAll();
    }

    @PostMapping("students")
    public Student createStudent(@Validated @RequestBody CreateStudent student) {
        return studentsRepository.save(Student
        .builder()
        .firstName(student.getFirstName())
        .name(student.getName())
        .build());
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<?> createStudent(@PathVariable("id") Integer id) {
        if(!studentsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id " + id);
        }

        return studentsRepository.findById(id)
                .map(student -> {
                    studentsRepository.delete(student);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));

    }
}