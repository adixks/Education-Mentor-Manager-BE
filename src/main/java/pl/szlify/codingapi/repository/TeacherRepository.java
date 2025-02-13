package pl.szlify.codingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szlify.codingapi.model.TeacherEntity;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findByIdAndDeletedFalse(Long id);
    boolean existsById(Long id);
    Optional<TeacherEntity> findByUsername(String username);
}
