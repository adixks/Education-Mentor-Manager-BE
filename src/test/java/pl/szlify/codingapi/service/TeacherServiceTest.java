//package pl.szlify.codingapi.service;
//
//import java.util.*;
//
//import com.github.javafaker.Faker;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import pl.szlify.codingapi.exceptions.LackOfTeacherException;
//import pl.szlify.codingapi.exceptions.LessonInFutureException;
//import pl.szlify.codingapi.mapper.TeacherMapper;
//import pl.szlify.codingapi.model.dto.TeacherShortDto;
//import pl.szlify.codingapi.model.dto.TeacherFullDto;
//import pl.szlify.codingapi.model.TeacherEntity;
//import pl.szlify.codingapi.repository.LessonRepository;
//import pl.szlify.codingapi.repository.TeacherRepository;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class TeacherServiceTest {
//
//    @Mock
//    private TeacherRepository teacherRepository;
//
//    @Mock
//    private LessonRepository lessonRepository;
//
//    @Mock
//    private TeacherMapper teacherMapper;
//
//    @InjectMocks
//    private TeacherService teacherService;
//
//    private Faker faker;
//
//    @BeforeEach
//    public void setup() {
//        faker = new Faker();
//    }
//
//    @Test
//    public void getTeachersListTest() {
//        // Given
//        TeacherEntity teacherEntity = createMockTeacherEntity();
//        TeacherShortDto teacherShortDto = createMockTeacherBasicInfoDto(teacherEntity);
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<TeacherEntity> pageOfEntities = new PageImpl<>(Collections.singletonList(teacherEntity));
//        when(teacherRepository.findAll(pageable)).thenReturn(pageOfEntities);
//        when(teacherMapper.toShortDto(teacherEntity)).thenReturn(teacherShortDto);
//
//        // When
//        Page<TeacherShortDto> result = teacherService.getList(pageable);
//
//        // Then
//        assertEquals(1, result.getContent().size());
//        assertEquals(teacherShortDto, result.getContent().get(0));
//    }
//
//    @Test
//    public void getTeacherTest() {
//        // Given
//        Long id = faker.number().randomNumber();
//        TeacherEntity teacherEntity = createMockTeacherEntity()
//                .setId(id)
//                .setFirstName(faker.name().fullName());
//
//        TeacherFullDto teacherFullDto = new TeacherFullDto()
//                .setId(teacherEntity.getId())
//                .setFirstName(teacherEntity.getFirstName());
//
//        when(teacherRepository.findById(id)).thenReturn(Optional.of(teacherEntity));
//        when(teacherMapper.toFullDto(teacherEntity)).thenReturn(teacherFullDto);
//
//        // When
//        TeacherFullDto result = teacherService.getTeacher(id);
//
//        // Then
//        assertEquals(teacherFullDto, result);
//    }
//
//    @Test
//    void getTeacher_shouldThrowsLackOfTeacherException() {
//        // Given
//        Long teacherId = 1L;
//        when(teacherRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        // When - Then
//        assertThrows(LackOfTeacherException.class, () -> teacherService.getTeacher(teacherId));
//    }
//
//    @Test
//    void updateTeacherLanguagesList_ThrowsLackOfTeacherException() {
//        // Given
//        Long teacherId = faker.number().randomNumber();
//        List<String> languagesList = List.of("java", "python");
//        when(teacherRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        // When - Then
//        assertThrows(LackOfTeacherException.class, () -> teacherService.updateTeacherLanguagesList(teacherId, languagesList));
//    }
//
//    @Test
//    void deleteTeacher_ThrowsLackOfTeacherException() {
//        // Given
//        Long teacherId = faker.number().randomNumber();
//        when(teacherRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        // When - Then
//        assertThrows(LackOfTeacherException.class, () -> teacherService.deleteTeacher(teacherId));
//    }
//
//    @Test
//    void deleteTeacher_ThrowsLessonInFutureException() {
//        // Given
//        Long teacherId = faker.number().randomNumber();
//        TeacherEntity teacherEntity = new TeacherEntity();
//        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(teacherEntity));
//        when(lessonRepository.existsByTeacherId(anyLong())).thenReturn(true);
//
//        // When - Then
//        assertThrows(LessonInFutureException.class, () -> teacherService.deleteTeacher(teacherId));
//    }
//
//    private TeacherEntity createMockTeacherEntity() {
//        return new TeacherEntity()
//                .setId(faker.number().randomNumber())
//                .setFirstName(faker.name().fullName());
//    }
//
//    private TeacherShortDto createMockTeacherBasicInfoDto(TeacherEntity teacherEntity) {
//        return new TeacherShortDto()
//                .setFirstName(teacherEntity.getFirstName());
//    }
//}
