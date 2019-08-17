package ru.icoltd.rvs.converter;

import org.springframework.format.Formatter;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ZoneDateTimeFormatter implements Formatter<ZonedDateTime> {

    @Override
    public ZonedDateTime parse(String text, Locale locale) {
        return ZonedDateTime.of(
                LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE),
                LocalTime.now(),
                ZoneId.of("UTC")
        );
    }

    @Override
    public String print(ZonedDateTime date, Locale locale) {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
