package com.iron.person_service.services;

import com.iron.person_service.models.Person;
import com.iron.person_service.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    //todo: implementar put y patch

    @Autowired
    private PersonRepository personRepository;

    //GET

    //todo: mandar excepciones personalizadas
    public ResponseEntity<?> getPersonByNickName(String nickName){
        Optional<Person> foundPerson = personRepository.findById(nickName);

        if (foundPerson.isPresent())
            return new ResponseEntity<>(foundPerson.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    //POST

    //todo: mandar excepciones personalizadas
    public ResponseEntity<?> savePerson(Person person){
        Optional<Person> foundPerson = personRepository.findById(person.getNickName());

        if (foundPerson.isPresent())
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Person savedPerson = personRepository.save(person);

            return new ResponseEntity<>(savedPerson, HttpStatus.OK);
        }
    }

    //DELETE

    public void deletePerson(String nickName){

        var personToDelete = personRepository.findById(nickName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        personRepository.delete(personToDelete);
    }
}
