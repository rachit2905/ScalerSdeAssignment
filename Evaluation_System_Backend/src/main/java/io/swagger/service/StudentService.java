package io.swagger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.model.Student;
import io.swagger.repository.StudentRepository;

@Service
@RestControllerAdvice
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void updateStudent(Integer studentId, Student student) {
        System.out.println(student);
        Optional<Student> tempStudent = studentRepository.findById(studentId);
        if (tempStudent.isPresent()) {
            Student oldStudent = tempStudent.get();
            oldStudent.setEvaluations(student.getEvaluations());
            oldStudent.setIsEvaluationFinal(student.isIsEvaluationFinal());
            oldStudent.setIsMentorAssigned(student.isIsMentorAssigned());
            oldStudent.setMentorId(student.getMentorId());
            // oldStudent.setName(student.getName());
            studentRepository.save(oldStudent);
        }
    }

    public Optional<Student> getStudentById(Integer studentId) {
        Optional<Student> tempStudent = studentRepository.findById(studentId);
        if (tempStudent.isPresent()) {

            return tempStudent;
        } else
            return null;
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;

    }

    public void deAssignMentor(Integer studentId, Integer mentorId) {
        Optional<Student> tempStudent = studentRepository.findById(studentId);
        if (tempStudent.isPresent()) {
            Student oldStudent = tempStudent.get();
            oldStudent.setMentorId(-1);
            studentRepository.save(oldStudent);
        }
    }

    public void assignMentor(Integer studentId, Integer mentorId) {
        Optional<Student> tempStudent = studentRepository.findById(studentId);
        if (tempStudent.isPresent()) {
            Student oldStudent = tempStudent.get();
            oldStudent.setMentorId(mentorId);
            studentRepository.save(oldStudent);
        }
    }

}
