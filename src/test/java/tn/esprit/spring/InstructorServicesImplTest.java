package tn.esprit.spring;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

@ExtendWith(MockitoExtension.class)
 class InstructorServicesImplTest {

    @InjectMocks
    private InstructorServicesImpl instructorServices;

    @Mock
    private IInstructorRepository instructorRepository;

    @Test
     void testAddInstructor() {
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        Instructor savedInstructor = instructorServices.addInstructor(instructor);

        assertEquals("John", savedInstructor.getFirstName());
        assertEquals("Doe", savedInstructor.getLastName());
        // Add more assertions here as needed
    }
}
