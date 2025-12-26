package koding_muda_nusantara.koding_muda_belajar.repository;

import koding_muda_nusantara.koding_muda_belajar.model.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Integer> {
    
    List<QuizAttempt> findByStudentStudentIdAndQuizQuizIdOrderByStartedAtDesc(Integer studentId, Integer quizId);
    
    long countByStudentStudentIdAndQuizQuizId(Integer studentId, Integer quizId);
    
    Optional<QuizAttempt> findFirstByStudentStudentIdAndQuizQuizIdOrderByScoreDesc(Integer studentId, Integer quizId);
    
    Optional<QuizAttempt> findFirstByStudentStudentIdAndQuizQuizIdAndCompletedAtIsNullOrderByStartedAtDesc(Integer studentId, Integer quizId);
    
    List<QuizAttempt> findByStudentStudentIdOrderByStartedAtDesc(Integer studentId);
    
    List<QuizAttempt> findByQuizQuizIdOrderByScoreDesc(Integer quizId);
    
    boolean existsByStudentStudentIdAndQuizQuizIdAndIsPassedTrue(Integer studentId, Integer quizId);
}
