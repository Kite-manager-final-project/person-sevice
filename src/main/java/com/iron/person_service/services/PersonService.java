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

    /**
     * Busca una persona por nickName
     * @param nickName el nickName de la persona a buscar
     * @return la persona encontrada con ese nickName
     * @throws PersonNotFoundException Si no existe ninguna persona con ese nickname
     */
    public Person getPersonByNickName(String nickName) {
        return personRepository.findById(nickName)
                .orElseThrow(() -> new PersonNotFoundException("Esa persona no existe en la base de datos"));
    }

    /**
     * @return Todas las personas en la base de datos
     */
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    //POST

    /**
     * Guarda una persona en la base de datos
     * @param person la persona a guardar
     * @return la persona que ha mandado el cliente
     * @throws PersonExistException si el cliente manda una persona con un nickName ya existente
     */
    public Person savePerson(Person person){
        Optional<Person> foundPerson = personRepository.findById(person.getNickName());

        if (foundPerson.isPresent())
            throw new PersonExistException("Ese usuario ya existe en la base de datos");

        return personRepository.save(person);

    }

    //DELETE

    /**
     * Elimina una persona
     * @param nickName el nickName de la persona a eliminar
     * @throws PersonNotFoundException si el cliente manda un nickName no existente
     */
    public void deletePerson(String nickName){

        Optional<Person> personToDelete = personRepository.findById(nickName);

        if (personToDelete.isEmpty())
            throw new PersonNotFoundException("La persona que intentas eliminar no existe");

        personRepository.delete(personToDelete.get());
    }

    //PUT

    /**
     * Permite modificar una persona
     * @param nickName el nickName de la persona a modificar
     * @param person la persona con los campos actualizados
     * @return la persona que ha mandado el cliente
     * @throws PersonNotFoundException si el cliente manda un nickname no existente
     */
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

    /**
     * Permite modificar la dirección de correo electrónico
     * @param nickName El nickname de la persona a modificar
     * @param person La persona con la dirección de correo electrónico modificada
     * @return La persona con todos los campos, además del su dirección de correo electrónico actualizada
     * @throws PersonNotFoundException Si el cliente le manda un nickName no existente
     */
    public Person updateEmailPerson(String nickName, UpdateEmailPersonDTO person){
        Optional<Person> foundPerson = personRepository.findById(nickName);

        if (foundPerson.isEmpty())
            throw new PersonNotFoundException("La persona que intentas modificar no existe en la base de datos");

        Person personToUpdate = foundPerson.get();

        personToUpdate.setEmail(person.getEmail());

        return personRepository.save(personToUpdate);
    }

    /**
     * Permite modificar el número de teléfono de una persona
     * @param nickName El nickName de la persona a modificar
     * @param person La persona con el número de teléfono modificado
     * @return La persona con todos los campos, además de su número de teléfono actualizado
     * @throws PersonNotFoundException Si el cliente manda un nickname no existente
     */
    public Person updatePhoneNumberPerson(String nickName, UpdatePhoneNumberPersonDTO person){
        Optional<Person> foundPerson = personRepository.findById(nickName);

        if (foundPerson.isEmpty())
            throw new PersonNotFoundException("La persona que intentas modificar no existe en la base de datos");

        Person personToUpdate = foundPerson.get();

        personToUpdate.setPhoneNumber(person.getPhoneNumber());

        return personRepository.save(personToUpdate);

    }
}
