package tn.esprit.spring.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.dto.InstructorDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
public class Instructor implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer numInstructor;
	String firstName;
	String lastName;
	LocalDate dateOfHire;
	@OneToMany
	private Set<Course> courses;
	private Instructor convertToEntity(InstructorDTO instructorDTO) {
		Instructor instructor = new Instructor();
		instructor.setNumInstructor(instructorDTO.getNumInstructor());
		instructor.setFirstName(instructorDTO.getFirstName());
		instructor.setLastName(instructorDTO.getLastName());
		instructor.setDateOfHire(instructorDTO.getDateOfHire());
		// Convert and set courses from CourseDTO to Course if necessary
		return instructor;
	}

	private InstructorDTO convertToDTO(Instructor instructor) {
		InstructorDTO instructorDTO = new InstructorDTO();
		instructorDTO.setNumInstructor(instructor.getNumInstructor());
		instructorDTO.setFirstName(instructor.getFirstName());
		instructorDTO.setLastName(instructor.getLastName());
		instructorDTO.setDateOfHire(instructor.getDateOfHire());
		// Convert and set courses from Course to CourseDTO if necessary
		return instructorDTO;
	}
}
