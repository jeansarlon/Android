package com.example.jean.webservice.Helpers;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by jeansarlon on 28/02/17.
 */

public class FormatDate {

    public String toEUA(String date){
        DateTimeFormatter format = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime dt = format.parseDateTime(date);

        return  dt.toString("yyyy-MM-dd");
    }
}
