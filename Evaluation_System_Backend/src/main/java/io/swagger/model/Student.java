package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Student
 */
@Entity(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Getter
@Setter
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-12T17:02:27.059051704Z[GMT]")

public class Student {
  @JsonProperty("student_id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer studentId;

  @JsonProperty("name")
  private String name = null;
  @JsonProperty("email")
  private String email = null;

  @JsonProperty("mentor_id")
  private Integer mentorId = -1;

  @JsonProperty("evaluations")
  @Valid
  @ElementCollection

  private List<Integer> evaluations = null;

  @JsonProperty("is_evaluation_final")
  private Boolean isEvaluationFinal = false;

  @JsonProperty("is_mentor_assigned")
  private Boolean isMentorAssigned = false;

  public Student studentId(Integer studentId) {
    this.studentId = studentId;
    return this;
  }

  /**
   * Get studentId
   * 
   * @return studentId
   **/
  @Schema(description = "")

  public Integer getStudentId() {
    return studentId;
  }

  public Student name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * 
   * @return name
   **/
  @Schema(description = "")

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Student mentorId(Integer mentorId) {
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

  public void setMentorId(Integer mentorId) {
    this.mentorId = mentorId;
  }

  public Student evaluations(List<Integer> evaluations) {
    this.evaluations = evaluations;
    return this;
  }

  public Student addEvaluationsItem(Integer evaluationsItem) {
    if (this.evaluations == null) {
      this.evaluations = new ArrayList<Integer>();
    }
    this.evaluations.add(evaluationsItem);
    return this;
  }

  /**
   * Get evaluations
   * 
   * @return evaluations
   **/
  @Schema(description = "")

  public List<Integer> getEvaluations() {
    return evaluations;
  }

  public void setEvaluations(List<Integer> evaluations) {
    this.evaluations = evaluations;
  }

  public Student isEvaluationFinal(Boolean isEvaluationFinal) {
    this.isEvaluationFinal = isEvaluationFinal;
    return this;
  }

  /**
   * Get isEvaluationFinal
   * 
   * @return isEvaluationFinal
   **/
  @Schema(description = "")

  public Boolean isIsEvaluationFinal() {
    return isEvaluationFinal;
  }

  public void setIsEvaluationFinal(Boolean isEvaluationFinal) {
    this.isEvaluationFinal = isEvaluationFinal;
  }

  public Student isMentorAssigned(Boolean isMentorAssigned) {
    this.isMentorAssigned = isMentorAssigned;
    return this;
  }

  /**
   * Get isMentorAssigned
   * 
   * @return isMentorAssigned
   **/
  @Schema(description = "")

  public Boolean isIsMentorAssigned() {
    return isMentorAssigned;
  }

  public void setIsMentorAssigned(Boolean isMentorAssigned) {
    this.isMentorAssigned = isMentorAssigned;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return Objects.equals(this.studentId, student.studentId) &&
        Objects.equals(this.name, student.name) &&
        Objects.equals(this.mentorId, student.mentorId) &&
        Objects.equals(this.evaluations, student.evaluations) &&
        Objects.equals(this.isEvaluationFinal, student.isEvaluationFinal) &&
        Objects.equals(this.isMentorAssigned, student.isMentorAssigned);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentId, name, mentorId, evaluations, isEvaluationFinal, isMentorAssigned);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Student {\n");

    sb.append("    studentId: ").append(toIndentedString(studentId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    mentorId: ").append(toIndentedString(mentorId)).append("\n");
    sb.append("    evaluations: ").append(toIndentedString(evaluations)).append("\n");
    sb.append("    isEvaluationFinal: ").append(toIndentedString(isEvaluationFinal)).append("\n");
    sb.append("    isMentorAssigned: ").append(toIndentedString(isMentorAssigned)).append("\n");
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
    return o.toString().replace("\n", "\n    ");
  }
}
