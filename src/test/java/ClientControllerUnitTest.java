//import ru.kpfu.itis.controller.ClientController;
//import ru.kpfu.itis.model.shop.Client;
//import ru.kpfu.itis.service.ClientService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import java.util.Arrays;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(ClientController.class)
//@ExtendWith(SpringExtension.class)
//public class ClientControllerUnitTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ClientService clientService;
//
//    @Test
//    public void testGetClientById() throws Exception {
//        Integer id = 1;
//        String name = "Test Client";
//        String email = "test@gmail.com";
//        Client client = Client.builder().id(id).name(name).email(email).build();
//        given(clientService.findById(id)).willReturn(Optional.of(client));
//
//        mockMvc.perform(get("/" + id))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.email").value(email));
//    }
//
//    @Test
//    public void testGetClientByName() throws Exception {
//        String name = "Test Client";
//        String email = "test@gmail.com";
//        Client client = Client.builder().name(name).email(email).build();
//        given(clientService.findByName(name)).willReturn(client);
//
//        mockMvc.perform(get("/name/" + name))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.email").value(email));
//    }
//
//    @Test
//    public void testGetClientsWithNoCart() throws Exception {
//        String name1 = "Client 1";
//        String name2 = "Client 2";
//        List<Client> clients = Arrays.asList(
//                Client.builder().id(1).name(name1).build(),
//                Client.builder().id(2).name(name2).build()
//        );
//        given(clientService.findByCartIsNull()).willReturn(clients);
//
//        mockMvc.perform(get("/no-cart"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].name").value(name1))
//                .andExpect(jsonPath("$[1].name").value(name2));
//    }
//
//    @Test
//    public void testVerify_success() throws Exception {
//        String code = "abc123";
//        given(clientService.verify(code)).willReturn(true);
//
//        mockMvc.perform(get("/verification").param("code", code))
//                .andExpect(status().isOk())
//                .andExpect(view().name("verification_success"));
//    }
//}
