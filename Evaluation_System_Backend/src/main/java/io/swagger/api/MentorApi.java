/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.47).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Mentor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-12T17:02:27.059051704Z[GMT]")
@Validated
public interface MentorApi {

    @Operation(summary = "Assign student to a mentor", description = "", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "mentor assigned successfully"),

            @ApiResponse(responseCode = "400", description = "mentor assignment failed"),

            @ApiResponse(responseCode = "404", description = "Student with this id not found") })
    @RequestMapping(value = "/mentor/assign/{mentor_id}/{student_id}", method = RequestMethod.PUT)
    ResponseEntity<Void> mentorAssignMentorIdStudentIdPut(
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("mentor_id") Integer mentorId,
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("student_id") Integer studentId);

    @Operation(summary = "Deassign a student from a mentor", description = "", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deassigning mentor successful"),

            @ApiResponse(responseCode = "400", description = "deassigning mentor failed"),

            @ApiResponse(responseCode = "404", description = "student with this id not found") })
    @RequestMapping(value = "/mentor/deassign/{mentor_id}/{student_id}", method = RequestMethod.PUT)
    ResponseEntity<Void> mentorDeassignMentorIdStudentIdPut(
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("student_id") Integer studentId,
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("mentor_id") Integer mentorId);

    @Operation(summary = "get mentor", description = "get mentor", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "mentor fecthed successfully"),

            @ApiResponse(responseCode = "404", description = "mentor with the mentor id requested not found") })
    @RequestMapping(value = "/mentor/{mentor_id}", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<Mentor> mentorMentorIdGet(
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("mentor_id") Integer mentorId);

    @Operation(summary = "edit student details", description = "edit student details", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student details updated successfully"),

            @ApiResponse(responseCode = "400", description = "Student details updation failed"),

            @ApiResponse(responseCode = "404", description = "Student with the id not found") })
    @RequestMapping(value = "/mentor/{mentor_id}", produces = { "application/json" }, method = RequestMethod.PUT)
    ResponseEntity<Void> mentorMentorIdPut(@RequestBody Mentor mentor,
            @Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("mentor_id") Integer mentorId);

    @Operation(summary = "Post a mentor", description = "Registers a mentor", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mentor created successfully"),

            @ApiResponse(responseCode = "400", description = "Mentor Registration Failed") })
    @RequestMapping(value = "/mentor/register", produces = { "application/json" }, consumes = {
            "application/json" }, method = RequestMethod.POST)
    ResponseEntity<Void> mentorRegisterPost(@RequestBody Mentor mentor);

}
