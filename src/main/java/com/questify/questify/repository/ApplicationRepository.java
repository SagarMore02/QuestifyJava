package com.questify.questify.repository;

import com.questify.questify.domain.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

  @Query(value = """
          SELECT COUNT(a.application_id) 
          FROM application_master a 
          JOIN exam_master e ON a.exam_id = e.exam_id 
          WHERE e.organizer_id = :organizerId
      """, nativeQuery = true)
  long countApplicationsByOrganizer(@Param("organizerId") Long organizerId);

  @Query(value = "SELECT COUNT(a.application_id) FROM application_master a JOIN exam_master e ON a.exam_id = e.exam_id WHERE e.organizer_id = :organizerId AND a.app_status != 'Active'", nativeQuery = true)
  long countPendingApplications(@Param("organizerId") Long organizerId);


}
