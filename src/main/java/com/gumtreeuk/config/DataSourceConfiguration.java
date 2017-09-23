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
import java.util.List;

@Configuration
public class DataSourceConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Value("${fileName}")
    String fileName;

    @Bean
    public List<Person> fileDataSource(FileParser fp) throws FileDataSourceException{
        List<Person> persons;

        try {
            ClassPathResource res = new ClassPathResource(fileName);
            persons = fp.getPersonsFromFile(res.getURI());
        }
        catch(IOException e){
            //logger.error("Error reading file!");
            throw new FileDataSourceException("Error reading file!");
        }
        catch(IllegalArgumentException e){
            //logger.error("Error parsing file!");
            throw new FileDataSourceException("Error parsing file!");
        }

        return persons;
    }
}
