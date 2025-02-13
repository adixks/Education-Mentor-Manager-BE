package pl.szlify.codingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szlify.codingapi.model.LessonEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long> {
    boolean existsByTeacherIdAndDateBetween(Long teacherId, LocalDateTime start, LocalDateTime end);
    boolean existsByTeacherId(Long teacherId);
    boolean existsByStudentId(Long studentId);
    List<LessonEntity> findByTeacherIdAndDateBetween(Long teacherId, LocalDateTime start, LocalDateTime end);
}
