package fr.istv.students.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.istv.students.models.Student;

public interface StudentsRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findById(Integer id);
}
