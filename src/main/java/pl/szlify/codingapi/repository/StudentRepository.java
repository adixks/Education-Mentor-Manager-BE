package pl.szlify.codingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szlify.codingapi.model.StudentEntity;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByIdAndDeletedFalse(Long id);
    Optional<StudentEntity> findByUsername(String username);
}
