package com.iron.person_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "persons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    private String nickName;

    private String name;


    private int phoneNumber;


    private String email;

}
