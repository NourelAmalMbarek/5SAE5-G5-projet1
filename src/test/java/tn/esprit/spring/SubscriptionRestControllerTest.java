import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.spring.controllers.SubscriptionRestController;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.services.ISubscriptionServices;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionRestControllerTest {

    @Mock
    private ISubscriptionServices subscriptionServices;

    @InjectMocks
    private SubscriptionRestController controller;

    @Test
    public void testGetSubscriptionsByDates() {
        // Définir des dates de début et de fin
        LocalDate startDate = LocalDate.of(2023, 10, 1);
        LocalDate endDate = LocalDate.of(2023, 10, 31);

        // Créer une liste de Subscription factice pour le test
        List<Subscription> mockSubscriptions = Arrays.asList(
                new Subscription(/* ... */),
                new Subscription(/* ... */)
                // Ajoutez autant d'objets Subscription que nécessaire pour le test
        );

        // Définir le comportement simulé de subscriptionServices.retrieveSubscriptionsByDates
        when(subscriptionServices.retrieveSubscriptionsByDates(startDate, endDate)).thenReturn(mockSubscriptions);

        // Appeler la méthode du contrôleur à tester
        List<Subscription> result = controller.getSubscriptionsByDates(startDate, endDate);

        // Vérifier que la méthode du service a été appelée avec les bonnes dates
        verify(subscriptionServices).retrieveSubscriptionsByDates(startDate, endDate);

        // Vérifier que le résultat est celui attendu
        assertEquals(mockSubscriptions, result);
    }
}
