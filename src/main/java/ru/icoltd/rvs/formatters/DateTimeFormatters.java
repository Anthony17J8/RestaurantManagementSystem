package ru.icoltd.rvs.formatters;

import org.springframework.format.Formatter;

import java.sql.Date;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeFormatters {

    public static class LocalDateFormatter implements Formatter<LocalDate> {

        @Override
        public LocalDate parse(String pattern, Locale locale) throws ParseException {
            return LocalDate.parse(pattern, DateTimeFormatter.ISO_LOCAL_DATE);
        }

        @Override
        public String print(LocalDate date, Locale locale) {
            return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }

    public static class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

        @Override
        public LocalDateTime parse(String pattern, Locale locale) throws ParseException {
            return LocalDateTime.parse(pattern, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }

        @Override
        public String print(LocalDateTime object, Locale locale) {
            return object.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
    }

    public static class DateFormatter implements Formatter<Date> {

        @Override
        public Date parse(String text, Locale locale) throws ParseException {
            return Date.valueOf(text);
        }

        @Override
        public String print(Date object, Locale locale) {
            return object.toString();
        }
    }
}