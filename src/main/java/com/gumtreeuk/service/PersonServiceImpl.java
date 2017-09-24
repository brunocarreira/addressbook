package com.gumtreeuk.service;

import com.gumtreeuk.dao.PersonRepository;
import com.gumtreeuk.domain.Person;
import com.gumtreeuk.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Period;
import java.util.Optional;

public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository personRepository;

    public long getMalesCount(){
        return personRepository.getMalesCount();
    }

    public Optional<Person> getOldestPerson(){
        return personRepository.getOldestPerson();
    }

    public int getDaysOlder(String nameA, String nameB) throws PersonNotFoundException{
        Person personA = personRepository.findByName(nameA);
        Person personB = personRepository.findByName(nameB);

        return Period.between(personA.getDtBirth(), personB.getDtBirth()).getDays();
    }
}
