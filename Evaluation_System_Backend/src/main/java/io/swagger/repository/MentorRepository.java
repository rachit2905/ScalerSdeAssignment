package io.swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.swagger.model.Mentor;
//import io.swagger.model.Student;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Integer> {
}