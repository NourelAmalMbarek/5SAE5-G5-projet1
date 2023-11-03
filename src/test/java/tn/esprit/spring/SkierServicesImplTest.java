import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.controllers.SkierRestController;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.services.ISkierServices;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class SkierServicesImplTest {

    private MockMvc mockMvc;

    @Mock
    private ISkierServices skierServices;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new SkierRestController(skierServices)).build();
    }

    @Test
    public void testRetrieveSkiersBySubscriptionType() throws Exception {
        // Créer une liste de skieurs que vous vous attendez à recevoir
        List<Skier> expectedSkiers = Arrays.asList(
                new Skier(/*...*/),
                new Skier(/*...*/)
                // Ajoutez autant de skieurs que nécessaire
        );

        // Spécifiez le comportement du service
        when(skierServices.retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL)).thenReturn(expectedSkiers);

        // Appelez l'API avec un TypeSubscription (par exemple TypeSubscription.ANNUAL)
        mockMvc.perform(get("/skier/getSkiersBySubscription")
                        .param("typeSubscription", "ANNUAL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(expectedSkiers.size())))
                .andExpect(jsonPath("$[0].numSkier").value(expectedSkiers.get(0).getNumSkier()))
                .andExpect(jsonPath("$[1].numSkier").value(expectedSkiers.get(1).getNumSkier()));
    }
}
