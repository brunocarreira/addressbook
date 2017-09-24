package com.gumtreeuk.dao;

import com.gumtreeuk.domain.Person;
import com.gumtreeuk.exceptions.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    public List<Person> findAll();

    public Person findByName(String name) throws PersonNotFoundException;

    public long getMalesCount();

    public Optional<Person> getOldestPerson();
}
