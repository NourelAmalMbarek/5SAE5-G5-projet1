package tn.esprit.spring.dto;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.entities.Course;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class InstructorDTO {
    private Long numInstructor;
    private String firstName;
    private String lastName;
    private LocalDate dateOfHire;

    private Set<Course> courses;


}