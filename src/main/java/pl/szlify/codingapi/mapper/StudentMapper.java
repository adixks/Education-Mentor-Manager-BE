package pl.szlify.codingapi.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.szlify.codingapi.model.*;
import pl.szlify.codingapi.model.dto.StudentShortDto;
import pl.szlify.codingapi.model.dto.StudentFullDto;
import pl.szlify.codingapi.model.dto.StudentShortListDto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    private final PasswordEncoder passwordEncoder;

    public StudentMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public StudentFullDto toFullDto(StudentEntity entity) {
        Set<Long> lessonIds = new HashSet<>();

        if (entity.getLessons() != null) {
            lessonIds = entity.getLessons().stream()
                    .map(LessonEntity::getId)
                    .collect(Collectors.toSet());
        }

        return new StudentFullDto()
                .setId(entity.getId())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setLanguage(entity.getLanguage().getName())
                .setRemoved(entity.getDeleted())
                .setTeacherId(entity.getTeacher().getId())
                .setLessonIds(lessonIds);
    }

    public StudentShortDto toShortDto(StudentEntity entity) {
        return new StudentShortDto()
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setLanguage(entity.getLanguage().getName())
                .setTeacherId(entity.getTeacher().getId());
    }

    public StudentShortListDto toShortListDto(StudentEntity entity) {
        return new StudentShortListDto()
                .setId(entity.getId())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setLanguage(entity.getLanguage().getName())
                .setTeacherId(entity.getTeacher().getId());
    }

    public StudentEntity toEntity(StudentShortDto studentShortDto) {
        return new StudentEntity()
                .setFirstName(studentShortDto.getFirstName())
                .setLastName(studentShortDto.getLastName())
                .setPassword(passwordEncoder.encode(studentShortDto.getPassword()))
                .setUsername(studentShortDto.getFirstName().toLowerCase() + "." + studentShortDto.getLastName().toLowerCase()) // Automatyczna nazwa użytkownika
                .setDeleted(false);
        // MAP THE TEACHER
        // MAP LANGUAGE
    }

    public StudentEntity toEntityUpdate(StudentEntity studentEntity, StudentShortDto studentShortDto) {
        return new StudentEntity()
                .setId(studentEntity.getId())
                .setFirstName(studentShortDto.getFirstName())
                .setLastName(studentShortDto.getLastName())
                .setDeleted(false);
        // MAP LANGUAGE
        // MAP THE TEACHER
    }
}
