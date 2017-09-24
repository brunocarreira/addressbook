package com.gumtreeuk.util;

import com.gumtreeuk.domain.Person;
import com.gumtreeuk.domain.Sex;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class FileParser {

    public List<Person> getPersonsFromFile(URI fileURI) throws IOException, DateTimeParseException, IllegalArgumentException{
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
