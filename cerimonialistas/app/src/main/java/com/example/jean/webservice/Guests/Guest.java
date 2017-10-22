package com.example.jean.webservice.Guests;

/**
 * Created by jeansarlon on 16/02/17.
 */

public class Guest {
    private String
            name
            ,lastname
            ,age
            ,table;
    private Integer id;

    public Guest(Integer id, String name,  String lastname,  String age, String table) {
        this.name = name;
        this.id = id;
        this.table = table;
        this.lastname = lastname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
