package com.geturgently.lindquist.registrar.students;

import com.geturgently.lindquist.registrar.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    //GET localhost:8080/students/123
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO getStudentById(@PathVariable("id") final Long id) {
        Student student = studentService.getStudentById(id);
//        if(student == null) {
//            throw new NotFoundException();
//        }

        StudentDTO studentDTO = studentMapper.mapToDTO(student);
        return studentDTO;
    }

    //POST localhost:8080/students?firstName=Musa&lastName=Moylam&emailAddress=mmoylam@geturgently.com&phoneNumber=8115551234
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO createStudent(@RequestParam(name="firstName", required = false) String firstName,
                                 @RequestParam(name="lastName", required = false) String lastName,
                                 @RequestParam(name="emailAddress", required = false) String emailAddress,
                                 @RequestParam(name="phoneNumber", required = false) String phoneNumber) {

        Student newStudent = Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .emailAddress(emailAddress)
                .build();

        Student savedStudent = studentService.createStudent(newStudent);
        StudentDTO studentDTO = studentMapper.mapToDTO(savedStudent);
        return studentDTO;
    }


}
