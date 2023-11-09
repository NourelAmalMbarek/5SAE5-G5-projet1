package tn.esprit.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.controllers.CourseRestController;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.services.ICourseServices;

import com.fasterxml.jackson.core.type.TypeReference;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@WebAppConfiguration
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
public class CourseRestControllerTest {

//    @Mock
//    private ICourseServices courseServices;
//
//    @InjectMocks
//    private CourseRestController courseRestController;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(courseRestController).build();
//    }
//
//    @Test
//    public void testAddCourse() throws Exception {
//        Course courseToAdd = new Course();
//        courseToAdd.setLevel(12);
//        courseToAdd.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
//        courseToAdd.setSupport(Support.SKI);
//        courseToAdd.setPrice(123.3f);
//        courseToAdd.setTimeSlot(2);
//
//        when(courseServices.addCourse(Mockito.any(Course.class))).thenReturn(courseToAdd);
//
//        String json = objectMapper.writeValueAsString(courseToAdd);
//
//        mockMvc.perform(post("/course/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.level").value(12))
//                .andExpect(jsonPath("$.typeCourse").value("COLLECTIVE_CHILDREN"))
//                .andExpect(jsonPath("$.support").value("SKI"))
//                .andExpect(jsonPath("$.price").value(123.3))
//                .andExpect(jsonPath("$.timeSlot").value(2));
//
//        verify(courseServices).addCourse(Mockito.any(Course.class));
//    }
//
//    @Test
//    public void testGetAllCourses() throws Exception {
//        List<Course> mockCourseList = new ArrayList<>();
//        Course course1 = new Course();
//        course1.setNumCourse(1L);
//        course1.setLevel(1);
//        course1.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
//        course1.setSupport(Support.SKI);
//        course1.setPrice(1F);
//        course1.setTimeSlot(1);
//
//        mockCourseList.add(course1);
//
//        Mockito.when(courseServices.retrieveAllCourses()).thenReturn(mockCourseList);
//
//        MvcResult result = mockMvc.perform(get("/course/all").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String jsonResponse = result.getResponse().getContentAsString();
//
//        List<Course> responseCourseList = objectMapper.readValue(jsonResponse, new TypeReference<List<Course>>(){});
//
//        assertEquals(mockCourseList.size(), responseCourseList.size());
//
//        Course expectedCourse = mockCourseList.get(0);
//        Course actualCourse = responseCourseList.get(0);
//
//        assertEquals(expectedCourse.getNumCourse(), actualCourse.getNumCourse());
//        assertEquals(expectedCourse.getLevel(), actualCourse.getLevel());
//        assertEquals(expectedCourse.getTypeCourse(), actualCourse.getTypeCourse());
//        assertEquals(expectedCourse.getSupport(), actualCourse.getSupport());
//        assertEquals(expectedCourse.getPrice(), actualCourse.getPrice());
//        assertEquals(expectedCourse.getTimeSlot(), actualCourse.getTimeSlot());
//    }
//    @Test
//    public void testGetCourseById() throws Exception {
//        Course course = new Course();
//        course.setNumCourse(1L);
//        course.setLevel(1);
//        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
//        course.setSupport(Support.SKI);
//        course.setPrice(29.99f);
//        course.setTimeSlot(2);
//
//        when(courseServices.retrieveCourse(Mockito.anyLong())).thenReturn(course);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/course/get/{id-course}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.numCourse").value(course.getNumCourse()))
//                .andExpect(jsonPath("$.level").value(course.getLevel()))
//                .andExpect(jsonPath("$.typeCourse").value(course.getTypeCourse().toString()))
//                .andExpect(jsonPath("$.support").value(course.getSupport().toString()))
//                .andExpect(jsonPath("$.price").value(course.getPrice()))
//                .andExpect(jsonPath("$.timeSlot").value(course.getTimeSlot()));
//
//        verify(courseServices).retrieveCourse(Mockito.anyLong());
//    }
}

