package com.gumtreeuk.service;

import com.gumtreeuk.AddressBookApplication;
import com.gumtreeuk.dao.PersonRepository;
import com.gumtreeuk.domain.Person;
import com.gumtreeuk.domain.Sex;
import com.gumtreeuk.exceptions.PersonNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressBookApplication.class)
public class PersonServiceTest {

    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Before
    public void setUp() throws PersonNotFoundException{
        MockitoAnnotations.initMocks(this);

        Person person1 = new Person("Name1", Sex.Male, LocalDate.of(1985, 1, 15));
        Person person2 = new Person("Name2", Sex.Female, LocalDate.of(1975, 1, 15));

        Mockito.when(personRepository.findByName("Name1")).thenReturn(person1);
        Mockito.when(personRepository.findByName("Name2")).thenReturn(person2);
        Mockito.when(personRepository.findByName(Mockito.contains("Invalid"))).thenThrow(new PersonNotFoundException());
    }

    @Test
    public void shouldGetCorrectDaysOlder() throws PersonNotFoundException{
        long diff = personService.getDaysOlder("Name1", "Name2");
        Assert.assertTrue(diff == 3653);
    }

    @Test(expected = PersonNotFoundException.class)
    public void shouldGetExceptionInvalidName() throws PersonNotFoundException{
        long diff = personService.getDaysOlder("NameInvalid", "Name2");
    }

}
