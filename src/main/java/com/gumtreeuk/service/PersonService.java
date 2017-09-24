package com.gumtreeuk.service;

import com.gumtreeuk.domain.Person;
import com.gumtreeuk.exceptions.PersonNotFoundException;

import java.util.Optional;

/**
 * Service responsible for Person functional requirements
 *
 * @author Bruno Carreira
 *
 */
public interface PersonService {

    long getMalesCount();

    Optional<Person> getOldestPerson();

    long getDaysOlder(String nameA, String nameB) throws PersonNotFoundException;

}
