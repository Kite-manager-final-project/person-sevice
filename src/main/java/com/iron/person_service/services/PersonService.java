package com.iron.person_service.services;

import com.iron.person_service.models.Person;
import com.iron.person_service.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    //GET

    public ResponseEntity<?> getPersonByNickName(String nickName){
        //Optional<Person> foundPerson = personRepository.
    }
}
