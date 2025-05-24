package com.iron.person_service.services;

import com.iron.person_service.dtos.UpdateEmailPersonDTO;
import com.iron.person_service.dtos.UpdatePhoneNumberPersonDTO;
import com.iron.person_service.exceptions.PersonExistException;
import com.iron.person_service.exceptions.PersonNotFoundException;
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


    @Autowired
    private PersonRepository personRepository;

    //GET

    public ResponseEntity<?> getPersonByNickName(String nickName){
        Optional<Person> foundPerson = personRepository.findById(nickName);

        if (foundPerson.isEmpty())
            throw new PersonNotFoundException("Esa persona no existe en la base de datos");

        return new ResponseEntity<>(foundPerson.get(), HttpStatus.OK);

    }

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    //POST


    public ResponseEntity<?> savePerson(Person person){
        Optional<Person> foundPerson = personRepository.findById(person.getNickName());

        if (foundPerson.isPresent())
            throw new PersonExistException("Ese usuario ya existe en la base de datos");

        Person savedPerson = personRepository.save(person);

        return new ResponseEntity<>(savedPerson, HttpStatus.OK);
    }

    //DELETE

    public void deletePerson(String nickName){

        Optional<Person> personToDelete = personRepository.findById(nickName);

        if (personToDelete.isEmpty())
            throw new PersonNotFoundException("La persona que intentas eliminar no existe");

        personRepository.delete(personToDelete.get());
    }

    //PUT

    public ResponseEntity<?> updatePerson(String nickName, Person person){
        Optional<Person> foundPerson = personRepository.findById(nickName);

        if (foundPerson.isEmpty())
            throw new PersonNotFoundException("La persona que intentas modificar no existe en la base de datos");

        Person personToUpdate = foundPerson.get();

        personToUpdate.setEmail(person.getEmail());
        personToUpdate.setName(person.getName());
        personToUpdate.setPhoneNumber(person.getPhoneNumber());

        Person savedPerson = personRepository.save(personToUpdate);

        return new ResponseEntity<>(savedPerson, HttpStatus.OK);

    }

    //PATCH

    public ResponseEntity<?> updateEmailPerson(String nickName, UpdateEmailPersonDTO person){
        Optional<Person> foundPerson = personRepository.findById(nickName);

        if (foundPerson.isEmpty())
            throw new PersonNotFoundException("La persona que intentas modificar no existe en la base de datos");

        Person personToUpdate = foundPerson.get();

        personToUpdate.setEmail(person.getEmail());

        Person savedPerson = personRepository.save(personToUpdate);

        return new ResponseEntity<>(savedPerson, HttpStatus.OK);
    }

    public ResponseEntity<?> updatePhoneNumberPerson(String nickName, UpdatePhoneNumberPersonDTO person){
        Optional<Person> foundPerson = personRepository.findById(nickName);

        if (foundPerson.isEmpty())
            throw new PersonNotFoundException("La persona que intentas modificar no existe en la base de datos");

        Person personToUpdate = foundPerson.get();

        personToUpdate.setPhoneNumber(person.getPhoneNumber());

        Person savedPerson = personRepository.save(personToUpdate);

        return new ResponseEntity<>(savedPerson, HttpStatus.OK);
    }
}
