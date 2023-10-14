package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

/**
 * mentor datatype
 */
@Entity(name = "mentors")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-12T17:02:27.059051704Z[GMT]")

public class Mentor {
  @JsonProperty("mentor_id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer mentorId;

  @JsonProperty("mentor_name")
  private String mentorName = null;

  @JsonProperty("students_assigned")
  @Valid
  @ElementCollection
  private List<Integer> studentsAssigned = null;

  @JsonProperty("is_evaluation_completed")
  private Boolean isEvaluationCompleted = false;

  public Mentor mentorId(Integer mentorId) {
    this.mentorId = mentorId;
    return this;
  }

  /**
   * Get mentorId
   * 
   * @return mentorId
   **/
  @Schema(description = "")

  public Integer getMentorId() {
    return mentorId;
  }

  public Mentor mentorName(String mentorName) {
    this.mentorName = mentorName;
    return this;
  }

  /**
   * Get mentorName
   * 
   * @return mentorName
   **/
  @Schema(description = "")

  public String getMentorName() {
    return mentorName;
  }

  public void setMentorName(String mentorName) {
    this.mentorName = mentorName;
  }

  public Mentor studentsAssigned(List<Integer> studentsAssigned) {
    this.studentsAssigned = studentsAssigned;
    return this;
  }

  public Mentor addStudentsAssignedItem(Integer studentsAssignedItem) {
    if (this.studentsAssigned == null) {
      this.studentsAssigned = new ArrayList<Integer>();
    }
    this.studentsAssigned.add(studentsAssignedItem);
    return this;
  }

  public Mentor removeStudentsAssignedItem(Integer studentsAssignedItem) {

    this.studentsAssigned.remove(studentsAssignedItem);
    return this;
  }

  /**
   * Get studentsAssigned
   * 
   * @return studentsAssigned
   **/
  @Schema(description = "")

  public List<Integer> getStudentsAssigned() {
    return studentsAssigned;
  }

  public void setStudentsAssigned(List<Integer> studentsAssigned) {
    this.studentsAssigned = studentsAssigned;
  }

  public Mentor isEvaluationCompleted(Boolean isEvaluationCompleted) {
    this.isEvaluationCompleted = isEvaluationCompleted;
    return this;
  }

  /**
   * Get isEvaluationCompleted
   * 
   * @return isEvaluationCompleted
   **/
  @Schema(description = "")

  public Boolean isIsEvaluationCompleted() {
    return isEvaluationCompleted;
  }

  public void setIsEvaluationCompleted(Boolean isEvaluationCompleted) {
    this.isEvaluationCompleted = isEvaluationCompleted;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Mentor mentor = (Mentor) o;
    return Objects.equals(this.mentorId, mentor.mentorId) &&
        Objects.equals(this.mentorName, mentor.mentorName) &&
        Objects.equals(this.studentsAssigned, mentor.studentsAssigned) &&
        Objects.equals(this.isEvaluationCompleted, mentor.isEvaluationCompleted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mentorId, mentorName, studentsAssigned, isEvaluationCompleted);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Mentor {\n");

    sb.append("    mentorId: ").append(toIndentedString(mentorId)).append("\n");
    sb.append("    mentorName: ").append(toIndentedString(mentorName)).append("\n");
    sb.append("    studentsAssigned: ").append(toIndentedString(studentsAssigned)).append("\n");
    sb.append("    isEvaluationCompleted: ").append(toIndentedString(isEvaluationCompleted)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n");
  }
}
