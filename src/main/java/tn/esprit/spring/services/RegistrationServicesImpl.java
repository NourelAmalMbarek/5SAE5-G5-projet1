package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
@Slf4j
@AllArgsConstructor
@Service
public class RegistrationServicesImpl implements  IRegistrationServices{

    private IRegistrationRepository registrationRepository;
    private ISkierRepository skierRepository;
    private ICourseRepository courseRepository;


    @Override
    public Registration addRegistrationAndAssignToSkier(Registration registration, Long numSkier) {
        Skier skier = skierRepository.findById(numSkier).orElse(null);
        registration.setSkier(skier);
        return registrationRepository.save(registration);
    }

    @Override
    public Registration assignRegistrationToCourse(Long numRegistration, Long numCourse) {
        Registration registration = registrationRepository.findById(numRegistration).orElse(null);
        Course course = courseRepository.findById(numCourse).orElse(null);
        if (registration != null && course != null) {
            registration.setCourse(course);
            return registrationRepository.save(registration);
        } else {
            // Gérez le cas où l'un des objets est nul (par exemple, en renvoyant null ou en lançant une exception).
            return null;
        }
    }

    @Transactional
    @Override
    public Registration addRegistrationAndAssignToSkierAndCourse(Registration registration, Long numSkieur, Long numCours) {
        Skier skier = skierRepository.findById(numSkieur).orElse(null);
        Course course = courseRepository.findById(numCours).orElse(null);

        if (skier == null || course == null) {
            return null;
        }

        if (registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(registration.getNumWeek(), skier.getNumSkier(), course.getNumCourse()) >= 1) {
            log.info("Sorry, you're already registered for this course of the week: " + registration.getNumWeek());
            return null;
        }

        int ageSkieur = calculateSkierAge(skier);

        switch (course.getTypeCourse()) {
            case INDIVIDUAL:
                return assignRegistration(registration, skier, course);

            case COLLECTIVE_CHILDREN:
                return processCollectiveChildrenCourse(registration, skier, course, ageSkieur);

            default:
                return processDefaultCourse(registration, skier, course, ageSkieur);
        }
    }

    private int calculateSkierAge(Skier skier) {
        return Period.between(skier.getDateOfBirth(), LocalDate.now()).getYears();
    }

    private Registration processCollectiveChildrenCourse(Registration registration, Skier skier, Course course, int ageSkieur) {
        if (ageSkieur < 16) {
            if (registrationRepository.countByCourseAndNumWeek(course, registration.getNumWeek()) < 6) {
                log.info("Course successfully added!");
                return assignRegistration(registration, skier, course);
            } else {
                log.info("Full Course! Please choose another week to register.");
            }
        } else {
            log.info("Sorry, your age doesn't allow you to register for this course!\nTry to Register for a Collective Adult Course...");
        }
        return null;
    }

    private Registration processDefaultCourse(Registration registration, Skier skier, Course course, int ageSkieur) {
        if (ageSkieur >= 16) {
            if (registrationRepository.countByCourseAndNumWeek(course, registration.getNumWeek()) < 6) {
                log.info("Course successfully added!");
                return assignRegistration(registration, skier, course);
            } else {
                log.info("Full Course! Please choose another week to register.");
            }
        } else {
            log.info("Sorry, your age doesn't allow you to register for this course!\nTry to Register for a Collective Child Course...");
        }
        return null;
    }

    private Registration assignRegistration (Registration registration, Skier skier, Course course){
        registration.setSkier(skier);
        registration.setCourse(course);
        return registrationRepository.save(registration);
    }

    @Override
    public List<Integer> numWeeksCourseOfInstructorBySupport(Long numInstructor, Support support) {
        return registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support);
    }

}
