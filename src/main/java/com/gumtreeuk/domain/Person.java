package com.gumtreeuk.domain;

import java.util.Date;

public class Person {

    private String name;

    private Sex sex;

    private Date dtBirth;

    public Person(String name, Sex sex, Date dtBirth){
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

    public Date getDtBirth() {
        return dtBirth;
    }

    public void setDtBirth(Date dtBirth) {
        this.dtBirth = dtBirth;
    }
}
