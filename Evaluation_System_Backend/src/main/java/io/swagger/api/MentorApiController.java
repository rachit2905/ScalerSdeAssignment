package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.model.Mentor;
import io.swagger.service.MentorService;
import io.swagger.service.StudentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-12T17:02:27.059051704Z[GMT]")
@RestController
public class MentorApiController implements MentorApi {

    @Autowired
    private MentorService mentorService;
    private static final Logger log = LoggerFactory.getLogger(MentorApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public MentorApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;

    }

    public ResponseEntity<Void> mentorAssignMentorIdStudentIdPut(
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("mentor_id") Integer mentorId,
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("student_id") Integer studentId) {
        mentorService.assignStudent(studentId, mentorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> mentorDeassignMentorIdStudentIdPut(
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("student_id") Integer studentId,
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("mentor_id") Integer mentorId) {
        mentorService.deAssignStudent(studentId, mentorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Mentor> mentorMentorIdGet(
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("mentor_id") Integer mentorId) {
        Optional<Mentor> mentor = mentorService.getMentorById(mentorId);
        if (mentor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        else {
            Mentor mentorTemp = mentor.get();
            return new ResponseEntity<>(mentorTemp, HttpStatus.OK);
        }

    }

    public ResponseEntity<Void> mentorMentorIdPut(
            @RequestBody Mentor mentor,
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("mentor_id") Integer mentorId) {
        mentorService.updateMentor(mentorId, mentor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> mentorRegisterPost(@RequestBody Mentor mentor) {
        mentorService.addMentor(mentor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
