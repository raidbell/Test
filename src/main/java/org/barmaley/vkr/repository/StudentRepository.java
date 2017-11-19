package org.barmaley.vkr.repository;

import org.barmaley.vkr.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
