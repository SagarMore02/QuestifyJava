package com.questify.questify.repository;


import com.questify.questify.domain.exam.Exam;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

  @Query(value = "SELECT * FROM EXAM_MASTER WHERE exam_id IN (SELECT exam_id FROM APPLICATION_MASTER WHERE application_id = :appId)", nativeQuery = true)
  List<Exam> findExamsByApplicationId(@Param("appId") Long applicationId);

  long countByOrganizerId(long organizerId);
}
