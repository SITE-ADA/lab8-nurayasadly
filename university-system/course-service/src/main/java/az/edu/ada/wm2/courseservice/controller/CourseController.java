package az.edu.ada.wm2.courseservice.controller;

import az.edu.ada.wm2.courseservice.model.dto.CourseRequestDto;
import az.edu.ada.wm2.courseservice.model.dto.CourseResponseDto;
import az.edu.ada.wm2.courseservice.model.dto.CourseStudentsResponseDto;
import az.edu.ada.wm2.courseservice.model.dto.EnrollmentResponseDto;
import az.edu.ada.wm2.courseservice.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(
        name = "Kurslar",
        description = "Kursların idarə edilməsi və tələbələrin kurslara yazılması"
)
@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @Operation(
            summary = "Yeni kurs yarat",
            description = "Yeni kurs yaradır və məlumatlarını sistemə əlavə edir."
    )
    public ResponseEntity<CourseResponseDto> createCourse(
            @Valid @RequestBody CourseRequestDto requestDto
    ) {
        CourseResponseDto createdCourse = courseService.createCourse(requestDto);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Bütün kursları göstər",
            description = "Sistemdə mövcud olan bütün kursları qaytarır."
    )
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "ID üzrə kursu göstər",
            description = "Verilmiş ID-yə əsasən bir kursu qaytarır."
    )
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/by-student-name")
    @Operation(
            summary = "Tələbə adına görə kursları gətir",
            description = "Tələbə adı və ya soyadı əsasında həmin tələbəyə aid kursları qaytarır."
    )
    public ResponseEntity<List<CourseResponseDto>> getCoursesByStudentsName(
            @RequestParam String name
    ) {
        return ResponseEntity.ok(courseService.getCoursesByStudentName(name));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Kursu yenilə",
            description = "Verilmiş ID-yə əsasən kurs məlumatlarını yeniləyir."
    )
    public ResponseEntity<CourseResponseDto> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDto requestDto
    ) {
        return ResponseEntity.ok(courseService.updateCourse(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Kursu sil",
            description = "Verilmiş ID-yə əsasən kursu silir."
    )
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{courseId}/students/{studentId}")
    @Operation(
            summary = "Tələbəni kursa yaz",
            description = "Tələbəni kursa yazır, tələbənin mövcudluğunu və prerequisite şərtini yoxlayır."
    )
    public ResponseEntity<EnrollmentResponseDto> enrollStudent(
            @PathVariable Long courseId,
            @PathVariable Long studentId
    ) {
        EnrollmentResponseDto responseDto =
                courseService.enrollStudent(courseId, studentId);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{courseId}/students")
    @Operation(
            summary = "Kursa yazılmış tələbələri göstər",
            description = "Verilmiş kursa yazılmış tələbələrin siyahısını qaytarır."
    )
    public ResponseEntity<CourseStudentsResponseDto> getCourseStudents(
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(courseService.getCourseStudents(courseId));
    }
}