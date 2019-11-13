package ru.icoltd.rvs.formatters;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        return LocalDateTime.of(LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE), LocalTime.now());
    }

    @Override
    public String print(LocalDateTime object, Locale locale) {
        return object.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
