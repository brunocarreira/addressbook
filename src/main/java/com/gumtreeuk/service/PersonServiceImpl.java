package com.gumtreeuk.service;

import com.gumtreeuk.dao.PersonRepository;
import com.gumtreeuk.domain.Person;
import com.gumtreeuk.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository personRepository;

    public long getMalesCount(){
        return personRepository.getMalesCount();
    }

    public Optional<Person> getOldestPerson(){
        return personRepository.getOldestPerson();
    }

    public long getDaysOlder(String nameA, String nameB) throws PersonNotFoundException{
        Person personA = personRepository.findByName(nameA);
        Person personB = personRepository.findByName(nameB);

        return Math.abs(ChronoUnit.DAYS.between(personA.getDtBirth(), personB.getDtBirth()));
    }
}
