package com.example.jean.webservice.Events;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by jeansarlon on 27/09/16.
 */

public class Party  implements Serializable {
    private Integer id;
    private String name,
            birthday_people,
            time,
            date;
    Locale l1 = new Locale("pt", "BR");

    public Party(Integer id, String name, String birthday_people, String date) {
        this.id = id;
        this.name = name;
        this.birthday_people = birthday_people;

        this.date = date;
        format();

    }

    private void format(){

        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime time = format.parseDateTime(date);


        DateTime dt = time;
        this.date = dt.toString("dd/MM/yyyy");
        this.time = dt.toString("HH:mm", l1);

    }

    public String getDate() {
        return date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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