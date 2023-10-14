package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.model.Student;
import io.swagger.service.StudentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-12T17:02:27.059051704Z[GMT]")
@RestController
public class StudentApiController implements StudentApi {

    private static final Logger log = LoggerFactory.getLogger(StudentApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Autowired
    private StudentService studentService;

    @org.springframework.beans.factory.annotation.Autowired
    public StudentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> studentAssignStudentIdMentorIdPut(
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("student_id") Integer studentId,
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("mentor_id") Integer mentorId) {
        studentService.assignMentor(studentId, mentorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> studentDeassignStudentIdMentorIdPut(
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("student_id") Integer studentId,
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("mentor_id") Integer mentorId) {
        studentService.deAssignMentor(studentId, mentorId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> studentEvaluateStudentIdPut(@RequestBody Student student,
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("student_id") Integer studentId) {
        studentService.updateStudent(studentId, student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Student>> studentGet() {
        List<Student> students = studentService.getAllStudents();
        if (students == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
    }

    public ResponseEntity<Void> studentRegisterPost(@RequestBody Student student) {
        studentService.addStudent(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Student> studentStudentIdGet(
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("student_id") Integer studentId) {
        Optional<Student> tempStudent = studentService.getStudentById(studentId);
        if (tempStudent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Student student = tempStudent.get();
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }

    public ResponseEntity<Void> studentStudentIdPut(
            @RequestBody Student student,
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("student_id") Integer studentId) {
        studentService.updateStudent(studentId, student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
