package com.gumtreeuk.dao;

import com.gumtreeuk.config.Datasource;
import com.gumtreeuk.domain.Person;
import com.gumtreeuk.domain.Sex;
import com.gumtreeuk.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonRepositoryImpl implements PersonRepository {

    private List<Person> persons;

    @Autowired
    public PersonRepositoryImpl(Datasource<Person> datasource){
        persons = datasource.getAddressBook();
    }

    public List<Person> findAll(){
        return persons;
    }

    public Person findByName(String name) throws PersonNotFoundException{
        if (name == null)
            throw new PersonNotFoundException("Null name");

        List<Person> filterPersonList = persons.stream()
                .filter(p -> name.equals(p.getName()))
                .collect(Collectors.toList());

        if (filterPersonList.isEmpty())
            throw new PersonNotFoundException("Name not found:"+name);

        return filterPersonList.get(0);
    }

    public long getMalesCount(){
        return persons.stream()
                    .filter(p -> Sex.Male.equals(p.getSex()))
                    .count();
    }

    public Optional<Person> getOldestPerson(){
        return persons.stream()
                .min(Comparator.comparing(Person::getDtBirth));
    }
}
