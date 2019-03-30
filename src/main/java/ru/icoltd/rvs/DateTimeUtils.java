package ru.icoltd.rvs;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeUtils {

    private DateTimeUtils() {
    }

    private final static LocalTime TIME_BOUND = LocalTime.of(11, 0);

    public static boolean isBetween(LocalDateTime src, LocalDateTime current) {
        LocalDateTime lowerBound = LocalDateTime.of(current.toLocalDate(), TIME_BOUND);
        LocalDateTime upperBound = lowerBound.plusDays(1);
        return src.isAfter(lowerBound) && src.isBefore(upperBound);
    }
}
