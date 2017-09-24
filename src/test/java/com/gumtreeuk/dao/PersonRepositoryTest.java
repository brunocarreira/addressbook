package com.gumtreeuk.dao;

import com.gumtreeuk.AddressBookApplication;
import com.gumtreeuk.config.Datasource;
import com.gumtreeuk.domain.Person;
import com.gumtreeuk.domain.Sex;
import com.gumtreeuk.exceptions.PersonNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressBookApplication.class)
public class PersonRepositoryTest {

    @MockBean
    private Datasource<Person> datasource;

    private PersonRepositoryImpl personRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<Person> personList = Arrays.asList(
                new Person("Name1", Sex.Male, LocalDate.of(1985, 1, 15)),
                new Person("Name2", Sex.Female, LocalDate.of(1975, 1, 15))
        );
        Mockito.when(datasource.getAddressBook()).thenReturn(personList);
        personRepository = new PersonRepositoryImpl(datasource);
    }

    @Test
    public void shouldGetAllPersons(){
        Assert.assertTrue(personRepository.findAll().size() == 2);
    }

    @Test(expected = PersonNotFoundException.class)
    public void shouldReturnExceptionForNullName() throws PersonNotFoundException{
        personRepository.findByName(null);
    }

    @Test(expected = PersonNotFoundException.class)
    public void shouldReturnExceptionForInvalidName() throws PersonNotFoundException{
        personRepository.findByName("Invalid");
    }

    @Test
    public void shouldReturnPersonWithName() throws PersonNotFoundException{
        Assert.assertTrue(personRepository.findByName("Name1").getName().equals("Name1"));
    }

    @Test
    public void shouldReturnName2AsOlder(){
        Assert.assertTrue(personRepository.getOldestPerson().get().getName().equals("Name2"));
    }

    @Test
    public void shouldReturnOneMale(){
        Assert.assertTrue(personRepository.getMalesCount() == 1);
    }

    @Test
    public void shouldReturnZeroMale(){
        Datasource<Person> datasource2 = Mockito.mock(Datasource.class);

        List<Person> personList = Arrays.asList(
                new Person("Name1", Sex.Female, LocalDate.of(85, 1, 15)),
                new Person("Name2", Sex.Female, LocalDate.of(75, 1, 15))
        );
        Mockito.when(datasource2.getAddressBook()).thenReturn(personList);
        PersonRepositoryImpl personRepository2 = new PersonRepositoryImpl(datasource2);

        Assert.assertTrue(personRepository2.getMalesCount() == 0);
    }

    @Test
    public void shouldReturnZeroMaleForEmptyDatasource(){
        Datasource<Person> datasource2 = Mockito.mock(Datasource.class);

        List<Person> personList = new ArrayList<>();
        Mockito.when(datasource2.getAddressBook()).thenReturn(personList);
        PersonRepositoryImpl personRepository2 = new PersonRepositoryImpl(datasource2);

        Assert.assertTrue(personRepository2.getMalesCount() == 0);
    }

}
