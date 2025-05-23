package com.iron.person_service.repositories;

import com.iron.person_service.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
}
