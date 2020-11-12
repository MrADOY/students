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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.istv.students.exception.ResourceNotFoundException;
import fr.istv.students.models.CreateStudent;
import fr.istv.students.models.Student;
import fr.istv.students.repositories.StudentsRepository;
import fr.istv.students.service.StudentsService;

@RestController
public class StudentsRoute {

    @Autowired
    StudentsRepository studentsRepository;

    @Autowired
    StudentsService studentService;

    @GetMapping("students/{id}")
    public Student getStudent(@PathVariable("id") Integer id) {
        return studentsRepository.findById(id).orElse(null);
    }

    @GetMapping("students")
    public List<Student> getAllStudents() {
        return studentsRepository.findAll();
    }

    @GetMapping("students/{id}/marks")
    public Student getStudentMarks(@PathVariable("id") Integer id, @RequestParam("subject") String subjet) {
        // do your treatment here
        return null;
    }
    
    @PostMapping("students")
    public Student createStudent(@Validated @RequestBody CreateStudent student) { 
        return studentsRepository.save(
            Student
            .builder()
            .name(student.getName())
            .firstName(student.getFirstName())
            .build()
        );
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
