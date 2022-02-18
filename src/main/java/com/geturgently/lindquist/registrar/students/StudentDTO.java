package com.geturgently.lindquist.registrar.students;

import com.geturgently.lindquist.registrar.DateTimeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private DateTimeDTO createdAt;
    private DateTimeDTO updatedAt;

}
