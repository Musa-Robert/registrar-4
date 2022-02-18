package com.geturgently.lindquist.registrar.students;

import com.geturgently.lindquist.registrar.DateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    private DateMapper dateMapper;

    @Autowired
    public StudentMapper(DateMapper dateMapper) {
        this.dateMapper = dateMapper;
    }

    public StudentDTO mapToDTO(Student student) {

        StudentDTO studentDTO = StudentDTO.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .emailAddress(student.getEmailAddress())
                .phoneNumber(student.getPhoneNumber())
                .createdAt(dateMapper.mapToDto(student.getCreatedAt()))
                .updatedAt(dateMapper.mapToDto(student.getUpdatedAt()))
                .build();

        return studentDTO;
    }

    public Student mapToEntity(StudentDTO studentDTO) {
        return null;
    }

}
