package com.example.jean.cerimonialistas;

/**
 * Created by jeansarlon on 27/09/16.
 */

public class Party {
    private String name, birthday_people;

    public Party(String name, String birthday_people){
        this.name = name;
        this.birthday_people = birthday_people;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday_people() {
        return birthday_people;
    }

    public void setBirthday_people(String birthday_people) {
        this.birthday_people = birthday_people;
    }
}