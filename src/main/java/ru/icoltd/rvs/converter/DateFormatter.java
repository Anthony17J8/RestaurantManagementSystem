package ru.icoltd.rvs.converter;

import org.springframework.format.Formatter;

import java.sql.Date;
import java.text.ParseException;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        return Date.valueOf(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        return object.toString();
    }
}
