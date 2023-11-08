package tn.esprit.spring;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.controllers.InstructorRestController;
import tn.esprit.spring.dto.InstructorDTO;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.services.IInstructorServices;

@ExtendWith(MockitoExtension.class)
public class InstructorRestControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private InstructorRestController instructorRestController;

    @Mock
    private IInstructorServices instructorServices;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(instructorRestController).build();
    }

    @Test
    public void testAddInstructor() throws Exception {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setFirstName("John");
        instructorDTO.setLastName("Doe");

        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorDTO.getFirstName());
        instructor.setLastName(instructorDTO.getLastName());

        when(instructorServices.addInstructor(any(Instructor.class))).thenReturn(instructor);

        mockMvc.perform(post("/instructor/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(instructorDTO)))
                .andExpect(status().isOk());
        // You can add more checks here such as checking the response body
    }

    // Utility method to convert objects to JSON strings
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
