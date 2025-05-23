package com.iron.person_service.services;

import com.iron.person_service.models.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Test
    @DisplayName("Guardo 2 personas")
    void savePerson(){

        Person person1, person2;

        person1 = new Person("hombre_de_la_rae", "Víctor Sanz", 634666424, "victoralexsanzcarrasc@gmail.com");

        person2 = new Person("auronplay", "Raúl Alvarez", 647951289, "raulalvarez@gmail.com");


        personService.savePerson(person1);

        personService.savePerson(person2);
    }



}