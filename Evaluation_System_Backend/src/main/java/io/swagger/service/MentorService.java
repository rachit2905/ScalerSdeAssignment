package io.swagger.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.model.Mentor;
import io.swagger.model.Student;
import io.swagger.repository.MentorRepository;
import io.swagger.repository.StudentRepository;

@Service
@RestControllerAdvice
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private StudentRepository studentRepository;

    public Optional<Mentor> getMentorById(Integer mentorId) {
        Optional<Mentor> mentor = mentorRepository.findById(mentorId);
        if (mentor.isPresent()) {
            return mentor;
        } else
            return null;
    }

    public void deAssignStudent(Integer studentId, Integer mentorId) {
        Optional<Mentor> tempMentor = mentorRepository.findById(mentorId);
        Optional<Student> tempStudent = studentRepository.findById(studentId);
        if (tempMentor.isPresent() && tempStudent.isPresent()) {
            Mentor oldMentor = tempMentor.get();
            Student oldStudent = tempStudent.get();
            if (oldMentor.getStudentsAssigned().contains(studentId)
                    && oldStudent.getMentorId() == mentorId) {
                oldMentor.removeStudentsAssignedItem(studentId);
                oldStudent.setMentorId(-1);
                oldStudent.setIsMentorAssigned(false);
            }
            mentorRepository.save(oldMentor);
            studentRepository.save(oldStudent);
        }
    }

    public void updateMentor(Integer mentorId, Mentor mentor) {
        Optional<Mentor> tempMentor = mentorRepository.findById(mentorId);
        if (tempMentor.isPresent()) {
            Mentor oldMentor = tempMentor.get();

            oldMentor.setIsEvaluationCompleted(mentor.isIsEvaluationCompleted());
            oldMentor.setMentorName(mentor.getMentorName());
            oldMentor.setStudentsAssigned(mentor.getStudentsAssigned());
            mentorRepository.save(oldMentor);
        }
    }

    public void assignStudent(Integer studentId, Integer mentorId) {
        Optional<Mentor> tempMentor = mentorRepository.findById(mentorId);
        Optional<Student> tempStudent = studentRepository.findById(studentId);

        if (tempMentor.isPresent() && tempStudent.isPresent()) {
            System.out.println(studentId + " " + mentorId);
            Mentor oldMentor = tempMentor.get();
            Student oldStudent = tempStudent.get();
            System.out.println(oldStudent.getIsMentorAssigned());
            if (oldMentor.getStudentsAssigned().size() < 4 && !oldStudent.getIsMentorAssigned()
                    && !oldMentor.getStudentsAssigned().contains(studentId)) {
                System.out.println("I am inside");
                oldMentor.addStudentsAssignedItem(studentId);
                oldStudent.setMentorId(mentorId);
                oldStudent.setIsMentorAssigned(true);
                mentorRepository.save(oldMentor);
                studentRepository.save(oldStudent);
            }
        }

    }

    public void addMentor(Mentor mentor) {
        mentorRepository.save(mentor);
    }

}
