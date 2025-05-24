package com.iron.person_service.controllers;

import com.iron.person_service.dtos.PersonDTO;
import com.iron.person_service.dtos.UpdateEmailPersonDTO;
import com.iron.person_service.dtos.UpdatePhoneNumberPersonDTO;
import com.iron.person_service.exceptions.PersonExistException;
import com.iron.person_service.exceptions.PersonNotFoundException;
import com.iron.person_service.models.Person;
import com.iron.person_service.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    //GET

    @GetMapping("/{nickName}")
    public ResponseEntity<?> getPersonByNickName(@PathVariable String nickName){
        try {
            return ResponseEntity.ok(personService.getPersonByNickName(nickName));
        }catch (PersonNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage(), "status", 404));
        }
    }

    @GetMapping("/allPersons")
    public ResponseEntity<List<Person>> getAllPersons(){

        return ResponseEntity.ok(personService.getAllPersons());
    }

    //POST

    @PostMapping
    public ResponseEntity<?> savePerson(@Valid @RequestBody Person person){
        try {
            return ResponseEntity.ok(personService.savePerson(person));
        }catch (PersonExistException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage(), "status", 409));
        }
    }

    //PUT

    @PutMapping("/{nickName}")
    public ResponseEntity<?> updatePerson(@PathVariable String nickName, @Valid @RequestBody PersonDTO person){
        try {
            return ResponseEntity.ok(personService.updatePerson(nickName, person));
        }catch (PersonNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage(), "status", 404));
        }
    }

    //PATCH

    @PatchMapping("/updateEmail/{nickName}")
    public ResponseEntity<?> updateEmail(@PathVariable String nickName, @Valid @RequestBody UpdateEmailPersonDTO person){
        try {
            return ResponseEntity.ok(personService.updateEmailPerson(nickName, person));
        }catch (PersonNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage(), "status", 404));
        }
    }

    @PatchMapping("/updatePhoneNumber/{nickName}")
    public ResponseEntity<?> updatePhoneNumber(@PathVariable String nickName, @Valid @RequestBody UpdatePhoneNumberPersonDTO person){
        try {
            return ResponseEntity.ok(personService.updatePhoneNumberPerson(nickName, person));
        }catch (PersonNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage(), "status", 404));
        }
    }


    //DELETE

    @DeleteMapping("/{nickName}")
    public ResponseEntity<?> deletePerson(@PathVariable String nickName){
        try {
            personService.deletePerson(nickName);
            return ResponseEntity.ok("Esa persona ha sido eliminada correctamente");
        }catch (PersonNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage(), "status", 404));
        }
    }

}
