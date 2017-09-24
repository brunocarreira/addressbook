package com.gumtreeuk.controller;

import com.gumtreeuk.domain.Person;
import com.gumtreeuk.exceptions.PersonNotFoundException;
import com.gumtreeuk.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Controller that receives requests from View(HTML) and bind model
 *
 * @author Bruno Carreira
 *
 */
@Controller
public class AddressBookController {

    @Autowired
    PersonService personService;

    @RequestMapping("/")
    public String index (Map<String, Object> model) {
        long malesCount = personService.getMalesCount();
        model.put("malesCount", malesCount);
        model.put("oldestName", personService.getOldestPerson().map(Person::getName).orElse("No person"));
        try {
            model.put("diffDays", personService.getDaysOlder("Paul Robinson", "Bill McKnight"));
        } catch (PersonNotFoundException e) {
            throw new RuntimeException (e.getMessage());
        }
        return "index";
    }
}