package com.gumtreeuk.domain;

import java.time.LocalDate;

/**
 * Domain model to represent Person
 *
 * @author Bruno Carreira
 *
 */
public class Person {

    private String name;

    private Sex sex;

    private LocalDate dtBirth;

    public Person(String name, Sex sex, LocalDate dtBirth){
        this.name = name;
        this.sex = sex;
        this.dtBirth = dtBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getDtBirth() {
        return dtBirth;
    }

    public void setDtBirth(LocalDate dtBirth) {
        this.dtBirth = dtBirth;
    }
}
