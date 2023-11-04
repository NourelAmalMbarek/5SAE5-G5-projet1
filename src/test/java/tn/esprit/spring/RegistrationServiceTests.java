package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.services.RegistrationServicesImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RegistrationServiceTests {
    @InjectMocks
    private RegistrationServicesImpl registrationService;

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ISkierRepository skierRepository;

    @Mock
    private ICourseRepository courseRepository;

    @Test
    public void testAddRegistrationAndAssignToSkier() {
        // Créer des objets Skier et Registration simulés pour le test
        Skier skier = new Skier();
        skier.setNumSkier(1L);

        Registration registration = new Registration();

        // Configurer les comportements simulés de vos référentiels (repositories)
        when(skierRepository.findById(1L)).thenReturn(java.util.Optional.of(skier));
        when(registrationRepository.save(registration)).thenReturn(registration);

        // Appeler la méthode à tester
        Registration result = registrationService.addRegistrationAndAssignToSkier(registration, 1L);

        // Vérifier que la méthode a renvoyé l'objet attendu
        assertEquals(registration, result);
    }

    @Test
    public void testAssignRegistrationToCourse() {
        // Créer des objets Registration et Course simulés pour le test
        Registration registration = new Registration();
        registration.setNumRegistration(1L);

        Course course = new Course();
        course.setNumCourse(2L);

        // Configurer les comportements simulés de vos référentiels (repositories)
        when(registrationRepository.findById(1L)).thenReturn(java.util.Optional.of(registration));
        when(courseRepository.findById(2L)).thenReturn(java.util.Optional.of(course));
        when(registrationRepository.save(registration)).thenReturn(registration);

        // Appeler la méthode à tester
        Registration result = registrationService.assignRegistrationToCourse(1L, 2L);

        // Vérifier que la méthode a renvoyé l'objet attendu
        assertEquals(registration, result);
    }

    // Ajoutez d'autres méthodes de test selon les besoins pour tester les différents scénarios de votre service.
    @Test
    public void testAddRegistrationAndAssignToSkierAndCourse_Success() {
        // Créez des objets Skier, Course et Registration simulés pour le test
        Skier skier = new Skier();
        skier.setNumSkier(1L);

        Course course = new Course();
        course.setNumCourse(2L);

        Registration registration = new Registration();

        // Configurer les comportements simulés de vos référentiels (repositories)
        when(skierRepository.findById(1L)).thenReturn(java.util.Optional.of(skier));
        when(courseRepository.findById(2L)).thenReturn(java.util.Optional.of(course));
        when(registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(registration.getNumWeek(), skier.getNumSkier(), course.getNumCourse())).thenReturn(0L);
        when(registrationRepository.countByCourseAndNumWeek(course, registration.getNumWeek())).thenReturn(5L);
        when(registrationRepository.save(registration)).thenReturn(registration);

        // Appeler la méthode à tester
        Registration result = registrationService.addRegistrationAndAssignToSkierAndCourse(registration, 1L, 2L);

        // Vérifier que la méthode a renvoyé l'objet attendu
        assertEquals(registration, result);
    }

    @Test
    public void testAddRegistrationAndAssignToSkierAndCourse_AlreadyRegistered() {
        // Créez des objets Skier, Course et Registration simulés pour le test
        Skier skier = new Skier();
        skier.setNumSkier(1L);

        Course course = new Course();
        course.setNumCourse(2L);

        Registration registration = new Registration();
        registration.setNumWeek(1); // Supposons que le skieur est déjà inscrit à la semaine 1 pour ce cours

        // Configurer les comportements simulés de vos référentiels (repositories)
        when(skierRepository.findById(1L)).thenReturn(java.util.Optional.of(skier));
        when(courseRepository.findById(2L)).thenReturn(java.util.Optional.of(course));
        when(registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(1, skier.getNumSkier(), course.getNumCourse())).thenReturn(1L);

        // Appeler la méthode à tester
        Registration result = registrationService.addRegistrationAndAssignToSkierAndCourse(registration, 1L, 2L);

        // Vérifier que la méthode a renvoyé null car le skieur est déjà inscrit à la semaine 1 pour ce cours
        assertNull(result);
    }

}
