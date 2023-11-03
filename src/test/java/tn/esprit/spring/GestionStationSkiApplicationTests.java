package tn.esprit.spring;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class PisteServicesImplTest {

	@Mock
	IPisteRepository pisteRepository;

	@InjectMocks
	 PisteServicesImpl pisteServices;



	@Test
	 void testRetrieveAllPistes() {
		// Create a list of Piste objects for testing
		List<Piste> pisteList = new ArrayList<>();
		pisteList.add(new Piste(1L, "Piste1", Color.RED, 100, 10, new HashSet<>()));
		pisteList.add(new Piste(2L, "Piste2", Color.BLUE, 150, 15, new HashSet<>()));

		// Define the behavior of the pisteRepository mock
		Mockito.when(pisteRepository.findAll()).thenReturn(pisteList);

		// Call the method to be tested
		List<Piste> result = pisteServices.retrieveAllPistes();

		// Assertions
		assertEquals(2, result.size());
		// Add more assertions based on your requirements
	}


	@Test
	 void testAddPiste() {
		// Create a Piste object for testing
		Piste piste = new Piste(1L, "Piste1", Color.RED, 100, 10, new HashSet<>());

		// Define the behavior of the pisteRepository mock
		Mockito.when(pisteRepository.save(Mockito.any(Piste.class))).thenReturn(piste);

		// Call the method to be tested
		Piste addedPiste = pisteServices.addPiste(piste);

		// Assertions
		assertNotNull(addedPiste);
		assertEquals(1L, addedPiste.getNumPiste());
		// Add more assertions based on your requirements
	}

	@Test
	void testRemovePiste() {
		// Define the behavior of the pisteRepository mock to return nothing when deleteById is called
		Mockito.doNothing().when(pisteRepository).deleteById(Mockito.anyLong());

		// Call the method to be tested
		pisteServices.removePiste(1L);

		// Verify that deleteById was called with the correct argument
		Mockito.verify(pisteRepository).deleteById(1L);
	}

	@Test
	void testRetrievePiste() {
		// Create a Piste object for testing
		Piste piste = new Piste(1L, "Piste1", Color.RED, 100, 10, new HashSet<>());

		// Define the behavior of the pisteRepository mock
		Mockito.when(pisteRepository.findById(1L)).thenReturn(Optional.of(piste));

		// Call the method to be tested
		Piste retrievedPiste = pisteServices.retrievePiste(1L);

		// Assertions
		assertNotNull(retrievedPiste);
		assertEquals(1L, retrievedPiste.getNumPiste());
		// Add more assertions based on your requirements
	}



}
