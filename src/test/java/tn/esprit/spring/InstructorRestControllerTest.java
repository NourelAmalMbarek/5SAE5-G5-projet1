package tn.esprit.spring;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import tn.esprit.spring.controllers.InstructorRestController;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.IInstructorServices;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class InstructorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IInstructorServices instructorServices;

/*    @Autowired
    private IInstructorRepository instructorRepository;*/


    @Test
    void whenGetAllInstructors_thenReturnInstructorsList() throws Exception {
        // arrange
        Instructor instructorOne = new Instructor(/* initialize with your data */);
        Instructor instructorTwo = new Instructor(/* initialize with your data */);
        List<Instructor> allInstructors = Arrays.asList(instructorOne, instructorTwo);

        given(instructorServices.retrieveAllInstructors()).willReturn(allInstructors);
        instructorTwo.setNumInstructor(123);
        instructorOne.setNumInstructor(123);
        // act and assert
        mockMvc.perform(get("/instructor/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.size()", is(allInstructors.size())))
                .andExpect((ResultMatcher) jsonPath("$[0].numInstructor", is(Math.toIntExact(instructorOne.getNumInstructor()))))
                .andExpect((ResultMatcher) jsonPath("$[1].numInstructor", is(Math.toIntExact(instructorTwo.getNumInstructor()))));
    }


/*

    @Test
    @Transactional
    void whenGetAllInstructorsIntegrationTest_thenReturnInstructorsList() throws Exception {
        // Arrange
        Instructor instructorOne = new Instructor();
        Instructor instructorTwo = new Instructor();
        instructorOne.setFirstName("Mohamed");
        instructorOne.setLastName("Ali");
        instructorOne.setDateOfHire(LocalDate.of(2021, 1, 1));
        instructorTwo.setFirstName("Mohamed");
        instructorTwo.setLastName("Ali");
        instructorTwo.setDateOfHire(LocalDate.of(2021, 1, 1));
        instructorOne = instructorRepository.save(instructorOne);
        instructorTwo = instructorRepository.save(instructorTwo);
        // Ensure that the instructors are saved and IDs are generated

        // Act & Assert
        mockMvc.perform(get("/instructor/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))) // Verify that the collection size is 2
                .andExpect(jsonPath("$[0].numInstructor", is(instructorOne.getNumInstructor().intValue())))
                .andExpect(jsonPath("$[1].numInstructor", is(instructorTwo.getNumInstructor().intValue())));
    }

*/


}








