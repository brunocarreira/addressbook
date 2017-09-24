package com.gumtreeuk.util;

import com.gumtreeuk.domain.Person;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class FileParserTest {

    FileParser fp = new FileParser();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWrongSex() throws IOException {
        ClassPathResource res = new ClassPathResource("AddressBookWrongSex");
        fp.getPersonsFromFile(res.getURI());
    }

    @Test(expected = IOException.class)
    public void shouldThrowIOExceptionFileNotFound() throws IOException {
        ClassPathResource res = new ClassPathResource("AddressBook1");
        fp.getPersonsFromFile(res.getURI());
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldThrowDateTimeParseExceptionInvalidDt() throws IOException {
        ClassPathResource res = new ClassPathResource("AddressBookInvalidDt");
        fp.getPersonsFromFile(res.getURI());
    }

    @Test
    public void shouldGet2PersonsEvenWithMoreAttr() throws IOException {
        ClassPathResource res = new ClassPathResource("AddressBookMoreAttr");
        List<Person> persons = fp.getPersonsFromFile(res.getURI());
        Assert.assertTrue(persons.size() == 2);
    }

    @Test
    public void shouldGet5Persons() throws IOException {
        ClassPathResource res = new ClassPathResource("AddressBook");
        List<Person> persons = fp.getPersonsFromFile(res.getURI());
        Assert.assertTrue(persons.size() == 5);
    }

    @Test
    public void shouldGetFirstBirthDate() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        ClassPathResource res = new ClassPathResource("AddressBook");
        List<Person> persons = fp.getPersonsFromFile(res.getURI());
        Assert.assertTrue("16/03/77".equals(persons.get(0).getDtBirth().format(formatter)));
    }

}
