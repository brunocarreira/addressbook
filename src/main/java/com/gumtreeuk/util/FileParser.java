package com.gumtreeuk.util;

import com.gumtreeuk.domain.Person;
import com.gumtreeuk.domain.Sex;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class to parse from file to in-memory data structure
 *
 * @author Bruno Carreira
 *
 */
@Component
public class FileParser {

    public List<Person> getPersonsFromFile(URI fileURI) throws Exception{
        List<String> strList = Files.readAllLines(Paths.get(fileURI));
        return strList.stream().map(string -> string.split(","))
                .map(array -> new Person(array[0].trim(), Sex.valueOf(array[1].trim()), parseDate(array[2].trim(), Constants.DATE_FORMAT)))
                .collect(Collectors.toList());
    }

    private LocalDate parseDate(String dateString, String formatString) throws DateTimeParseException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
        return LocalDate.parse(dateString, formatter);
    }
}
