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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileParser {

    public List<Person> getPersonsFromFile(URI fileURI) throws IOException, IllegalArgumentException{
        List<String> strList = Files.readAllLines(Paths.get(fileURI));
        List<Person> persons = strList.stream().map(string -> string.split(","))
                .map(array -> new Person(array[0].trim(), Sex.valueOf(array[1].trim()), parseDate(array[2].trim(), Constants.DATE_FORMAT)))
                .collect(Collectors.toList());
        return persons;
    }

    private Date parseDate(String dateString, String formatString) throws IllegalArgumentException{
        Date dt;
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        try {
            dt = format.parse(dateString);
        }
        catch(ParseException e){
            throw new IllegalArgumentException();
        }
        return dt;
    }
}
