package org.barmaley.vkr.repository;

import org.barmaley.vkr.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query(value = "select count(*) from (select avg(ex.assessment) from " +
            "exam ex group by ex.student_id having avg(ex.assessment) >= 4.6) as sq",
            nativeQuery = true)
    int countSuccessStudents(@Param("param") float param);
}
