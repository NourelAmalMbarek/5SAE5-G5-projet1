package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.dto.InstructorDTO;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.services.IInstructorServices;

import java.util.List;

@Tag(name = "\uD83D\uDC69\u200D\uD83C\uDFEB Instructor Management")
@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorRestController {

    private final IInstructorServices instructorServices;

    @Operation(description = "Add Instructor")
    @PostMapping("/add")
    public Instructor addInstructor(@RequestBody InstructorDTO instructor){
        Instructor instructorEntity=convertToEntity(instructor);
        return  instructorServices.addInstructor(instructorEntity);
    }
    @Operation(description = "Add Instructor and Assign To Course")
    @PutMapping("/addAndAssignToCourse/{numCourse}")
    public InstructorDTO addAndAssignToInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable("numCourse") Long numCourse){
        // Convert InstructorDTO to Instructor entity
        Instructor instructor = convertToEntity(instructorDTO);
        // Perform the operation
        Instructor updatedInstructor = instructorServices.addInstructorAndAssignToCourse(instructor, numCourse);
        // Convert the updated Instructor entity back to DTO
        return convertToDTO(updatedInstructor);
    }
    @Operation(description = "Retrieve all Instructors")
    @GetMapping("/all")
    public List<Instructor> getAllInstructors(){
        return instructorServices.retrieveAllInstructors();
    }

    @Operation(description = "Update Instructor ")
    @PutMapping("/update")
    public InstructorDTO updateInstructor(@RequestBody InstructorDTO instructorDTO){
        // You would need to convert InstructorDTO to Instructor entity before saving
        Instructor instructor = convertToEntity(instructorDTO);
        Instructor updatedInstructor = instructorServices.updateInstructor(instructor);
        // After saving, convert the entity back to DTO to return
        return convertToDTO(updatedInstructor);
    }

    @Operation(description = "Retrieve Instructor by Id")
    @GetMapping("/get/{id-instructor}")
    public Instructor getById(@PathVariable("id-instructor") Long numInstructor){
        return instructorServices.retrieveInstructor(numInstructor);
    }
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
