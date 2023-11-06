package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SkierServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class GestionStationSkiApplicationTests {

	@Mock
	private ISkierRepository skierRepository;
	@Mock
	private ISubscriptionRepository subscriptionRepository;

	@Mock
	private IPisteRepository pisteRepository;
	@InjectMocks
	private SkierServicesImpl skierServices;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test

	void testAddSkier() {
		Skier skier = new Skier();
		skier.setNumSkier(1L);
		skier.setFirstName("John");
		skier.setLastName("Doe");
		skier.setDateOfBirth(LocalDate.of(1990, 5, 20));
		skier.setCity("New York");

		when(skierRepository.save(any(Skier.class))).thenReturn(skier);

		Skier result = skierServices.addSkier(skier);
		assertEquals(skier, result);
	}

	@Test
	void testRetrieveSkier() {
		Skier skier = new Skier();
		skier.setNumSkier(1L);
		skier.setFirstName("John");
		skier.setLastName("Doe");
		skier.setDateOfBirth(LocalDate.of(1990, 5, 20));
		skier.setCity("New York");

		when(skierRepository.findById(anyLong())).thenReturn(Optional.of(skier));

		Skier result = skierServices.retrieveSkier(1L);
		assertEquals(skier, result);
	}

	@Test
	void testRetrieveAllSkiers() {
		List<Skier> skiers = new ArrayList<>();


		when(skierRepository.findAll()).thenReturn(skiers);

		List<Skier> result = skierServices.retrieveAllSkiers();
		assertEquals(skiers, result);
	}

	@Test
	void testAssignToSubscription() {
		Skier skier = new Skier();
		Subscription subscription = new Subscription();
		when(skierRepository.findById(anyLong())).thenReturn(Optional.of(skier));
		when(subscriptionRepository.findById(anyLong())).thenReturn(Optional.of(subscription));
		when(skierRepository.save(any(Skier.class))).thenReturn(skier);

		Skier result = skierServices.assignSkierToSubscription(1L, 1L);
		assertNotNull(result);
		assertEquals(subscription, result.getSubscription());
	}



	@Test
	void testAssignToPiste() {
		Skier skier = new Skier();
		Piste piste = new Piste(); // Assuming you have the Piste object
		skier.setPistes(new HashSet<>()); // Set empty set of pistes initially
		when(skierRepository.findById(anyLong())).thenReturn(Optional.of(skier));
		when(pisteRepository.findById(anyLong())).thenReturn(Optional.of(piste));
		when(skierRepository.save(any(Skier.class))).thenReturn(skier);

		Skier result = skierServices.assignSkierToPiste(1L, 1L);
		assertNotNull(result.getPistes());
		assertTrue(result.getPistes().contains(piste));
	}


	@Test
	void testRetrieveSkiersBySubscriptionType() {
		List<Skier> skiers = new ArrayList<>();
		when(skierServices.retrieveSkiersBySubscriptionType(any(TypeSubscription.class))).thenReturn(skiers);

		List<Skier> result = skierServices.retrieveSkiersBySubscriptionType(TypeSubscription.MONTHLY);
		assertEquals(skiers, result);
	}

	@Test
	void testGetById() {
		Skier skier = new Skier();
		when(skierRepository.findById(anyLong())).thenReturn(Optional.of(skier));

		Skier result = skierServices.retrieveSkier(1L);
		assertEquals(skier, result);
	}


	@Test
	void testDeleteById() {
		skierServices.removeSkier(1L);
		// verify the delete operation here if necessary
	}

	@Test
	void testGetAllSkiers() {
		List<Skier> skiers = new ArrayList<>();
		when(skierServices.retrieveAllSkiers()).thenReturn(skiers);

		List<Skier> result = skierServices.retrieveAllSkiers();
		assertEquals(skiers, result);
	}
}