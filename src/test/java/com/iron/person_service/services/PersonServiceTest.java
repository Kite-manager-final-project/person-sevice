package com.iron.person_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iron.person_service.dtos.*;
import com.iron.person_service.models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //POST

    @Test
    @DisplayName("Guardar una persona")
    @Transactional //me aseguro que no se realice la modificación en la base de datos
    void savePerson() throws Exception {
        Person person = new Person("kino", "Joaquin Perez", 632459871, "kino@gmail.com");

        String personJSON = objectMapper.writeValueAsString(person);

        MvcResult result = mockMvc.perform(post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personJSON)
        ).andExpect(status().isOk()).andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    //GET

    @Test
    @DisplayName("Obtener todas las personas")
    void findAllPersons() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/person/allPersons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Busco una persona por nickname que exista")
    void findExistingPerson() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/person/hombre_de_la_rae")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Busco una persona que no exista")
    void findNonExistingPerson() throws Exception {

        mockMvc.perform(get("/api/person/flow")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Esa persona no existe en la base de datos"));
    }

    //PATCH

    @Test
    @DisplayName("Actualizar email de una persona")
    @Transactional
    void updateEmail() throws Exception {

        String newEmail = "gurrucharri@gmail.com";

        UpdateEmailPersonDTO request = new UpdateEmailPersonDTO(newEmail);
        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(patch("/api/person/updateEmail/developer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(newEmail));
    }

    @Test
    @DisplayName("Actualizar número de teléfono de una persona")
    @Transactional
    void updatePhoneNumber() throws Exception {

        String newPhoneNumber = "987654321";

        UpdatePhoneNumberPersonDTO request = new UpdatePhoneNumberPersonDTO(newPhoneNumber);
        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(patch("/api/person/updatePhoneNumber/developer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(newPhoneNumber));
    }

    @Test
    @DisplayName("Intentar actualizar email de una persona inexistente")
    void updateEmailNonExistingPerson() throws Exception {

        String newEmail = "florentino@gmail.com";

        UpdateEmailPersonDTO request = new UpdateEmailPersonDTO(newEmail);
        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(patch("/api/person/updateEmail/florentino")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("La persona que intentas modificar no existe en la base de datos"));
    }

    @Test
    @DisplayName("Intentar modificar el número de teléfono de una persona inexistente")
    void updateTelephoneNonExistingPerson() throws Exception {

        String newPhoneNumber = "614569873";

        UpdatePhoneNumberPersonDTO request = new UpdatePhoneNumberPersonDTO(newPhoneNumber);
        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(patch("/api/person/updatePhoneNumber/florentino")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("La persona que intentas modificar no existe en la base de datos"));

    }


    //DELETE

    @Test
    @DisplayName("Eliminar a una persona de la base de datos")
    @Transactional
    void deletePerson() throws Exception {

        mockMvc.perform(delete("/api/person/tester")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Esa persona ha sido eliminada correctamente"));

    }

    @Test
    @DisplayName("Eliminar a una persona que no existe en la base de datos")
    void deleteNonExistingPerson() throws Exception {

        mockMvc.perform(delete("/api/person/florentino")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("La persona que intentas eliminar no existe"));

    }

    //PUT

    @Test
    @DisplayName("Modificar una persona por completo")
    @Transactional
    void updatePerson() throws Exception {
        PersonDTO request = new PersonDTO("Salva Gurrucharri", "657631245", "salvador@gmail.com");

        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(put("/api/person/developer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(request.getName()))
                .andExpect(jsonPath("$.phoneNumber").value(request.getPhoneNumber()))
                .andExpect(jsonPath("$.email").value(request.getEmail()));
    }

    @Test
    @DisplayName("Modificar una persona no existente")
    void updateUnexistingPerson() throws Exception {
        PersonDTO request = new PersonDTO("Javier Perez", "691452397", "javiperez@gmail.com");

        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(put("/api/person/fernan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("La persona que intentas modificar no existe en la base de datos"));

    }

}