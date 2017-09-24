package com.gumtreeuk.service;

import com.gumtreeuk.domain.Person;
import com.gumtreeuk.exceptions.PersonNotFoundException;

import java.util.Optional;

public interface PersonService {

    public long getMalesCount();

    public Optional<Person> getOldestPerson();

    public int getDaysOlder(String nameA, String nameB) throws PersonNotFoundException;

}
