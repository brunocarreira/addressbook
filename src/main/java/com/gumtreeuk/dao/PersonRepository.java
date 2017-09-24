package com.gumtreeuk.dao;

import com.gumtreeuk.domain.Person;
import com.gumtreeuk.exceptions.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * DAO Repository to access Person Data
 *
 * @author Bruno Carreira
 *
 */
public interface PersonRepository {

    /**
     * Returns all Persons
     */
    List<Person> findAll();

    /**
     * Returns a Person by name. Throws a customized exception for null/invalid name
     */
    Person findByName(String name) throws PersonNotFoundException;

    /**
     * Returns the number of Males in List
     */
    long getMalesCount();

    /**
     * Returns the oldest person in List. Optional was used just in case the list is empty.
     */
    Optional<Person> getOldestPerson();
}
