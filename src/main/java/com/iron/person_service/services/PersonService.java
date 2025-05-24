package com.iron.person_service.services;

import com.iron.person_service.dtos.PersonDTO;
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

    public Person getPersonByNickName(String nickName) {
        return personRepository.findById(nickName)
                .orElseThrow(() -> new PersonNotFoundException("Esa persona no existe en la base de datos"));
    }

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    //POST


    public Person savePerson(Person person){
        Optional<Person> foundPerson = personRepository.findById(person.getNickName());

        if (foundPerson.isPresent())
            throw new PersonExistException("Ese usuario ya existe en la base de datos");

        return personRepository.save(person);

    }

    //DELETE

    public void deletePerson(String nickName){

        Optional<Person> personToDelete = personRepository.findById(nickName);

        if (personToDelete.isEmpty())
            throw new PersonNotFoundException("La persona que intentas eliminar no existe");

        personRepository.delete(personToDelete.get());
    }

    //PUT

    public Person updatePerson(String nickName, PersonDTO person){
        Optional<Person> foundPerson = personRepository.findById(nickName);

        if (foundPerson.isEmpty())
            throw new PersonNotFoundException("La persona que intentas modificar no existe en la base de datos");

        Person personToUpdate = foundPerson.get();

        personToUpdate.setEmail(person.getEmail());
        personToUpdate.setName(person.getName());
        personToUpdate.setPhoneNumber(person.getPhoneNumber());

        return personRepository.save(personToUpdate);

    }

    //PATCH

    public Person updateEmailPerson(String nickName, UpdateEmailPersonDTO person){
        Optional<Person> foundPerson = personRepository.findById(nickName);

        if (foundPerson.isEmpty())
            throw new PersonNotFoundException("La persona que intentas modificar no existe en la base de datos");

        Person personToUpdate = foundPerson.get();

        personToUpdate.setEmail(person.getEmail());

        return personRepository.save(personToUpdate);
    }

    public Person updatePhoneNumberPerson(String nickName, UpdatePhoneNumberPersonDTO person){
        Optional<Person> foundPerson = personRepository.findById(nickName);

        if (foundPerson.isEmpty())
            throw new PersonNotFoundException("La persona que intentas modificar no existe en la base de datos");

        Person personToUpdate = foundPerson.get();

        personToUpdate.setPhoneNumber(person.getPhoneNumber());

        return personRepository.save(personToUpdate);

    }
}
