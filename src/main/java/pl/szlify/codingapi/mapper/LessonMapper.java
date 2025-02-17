package pl.szlify.codingapi.mapper;

import org.springframework.stereotype.Component;
import pl.szlify.codingapi.model.LessonEntity;
import pl.szlify.codingapi.model.dto.LessonDto;
import pl.szlify.codingapi.model.dto.LessonListDto;

@Component
public class LessonMapper {

    public LessonDto toDto(LessonEntity entity) {
        return new LessonDto()
                .setTeacherId(entity.getTeacher().getId())
                .setStudentId(entity.getStudent().getId())
                .setDate(entity.getDate());
    }

    public LessonListDto toListDto(LessonEntity entity) {
        return new LessonListDto()
                .setId(entity.getId())
                .setTeacherId(entity.getTeacher().getId())
                .setStudentId(entity.getStudent().getId())
                .setDate(entity.getDate());
    }

    public LessonEntity toEntity(LessonDto model) {
        return new LessonEntity()
                .setDate(model.getDate());
        // MAP THE TEACHER AND THE STUDENT
    }
}
