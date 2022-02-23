package com.geturgently.lindquist.registrar.students;

import com.geturgently.lindquist.registrar.DateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentMapperTest {


    private StudentMapper studentMapper;

    @BeforeEach
    void init(){
        studentMapper = new StudentMapper(new DateMapper());
    }

    @Test
    void testMapToDTO() {
        //public StudentDTO mapToDTO(Student student) {
        // }

        Student student = Student.builder()
                .createdAt(null)
                .emailAddress("mmoy@gmail.com")
                .firstName("Musa")
                .id(1L)
                .lastName("Moylam")
                .phoneNumber("(811) 555-1234")
                .updatedAt(null)
                .build();

        StudentDTO expectedStudentDTO = StudentDTO.builder()
                .createdAt(null)
                .emailAddress("mmoy@gmail.com")
                .firstName("Musa")
                .id(1L)
                .lastName("Moylam")
                .phoneNumber("(811) 555-1234")
                .updatedAt(null)
                .build();

        StudentDTO actualStudentDTO = studentMapper.mapToDTO(student);

        assertEquals(expectedStudentDTO, actualStudentDTO);
    }

}
