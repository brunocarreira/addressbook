package com.gumtreeuk.config;

import com.gumtreeuk.domain.Person;
import com.gumtreeuk.exceptions.FileDataSourceException;
import com.gumtreeuk.util.FileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;

@Configuration
public class DataSourceConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Value("${fileName}")
    String fileName;

    @Bean
    public Datasource<Person> fileDataSource(FileParser fp) throws FileDataSourceException{
        Datasource<Person> datasource = new Datasource<Person>();

        try {
            ClassPathResource res = new ClassPathResource(fileName);
            List<Person> persons = fp.getPersonsFromFile(res.getURI());
            datasource.setAddressBook(persons);
        }
        catch(IOException e){
            //logger.error("Error reading file!");
            throw new FileDataSourceException("Error reading file!");
        }
        catch(IllegalArgumentException|DateTimeParseException e){
            //logger.error("Error parsing file!");
            throw new FileDataSourceException("Error parsing file!");
        }
        catch(Exception e){
            throw new FileDataSourceException("Invalid File!");
        }

        return datasource;
    }
}
