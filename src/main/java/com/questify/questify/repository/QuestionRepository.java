package com.questify.questify.repository;

import com.questify.questify.domain.question.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

  List<Question> findAllByExamId(long examId);

}
