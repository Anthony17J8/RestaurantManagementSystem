package ru.icoltd.rvs.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    private final static LocalTime TIME_BOUND = LocalTime.of(11, 0);

    // DataBase doesn't support LocalDate.MIN/MAX
    public static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);

    public static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

    private DateTimeUtils() {
    }

    public static boolean isWithinVoteInterval(LocalDateTime src, LocalDateTime now) {
        var timePoint = LocalDateTime.of(now.toLocalDate(), TIME_BOUND);
        LocalDateTime lowerBound;
        LocalDateTime upperBound;
        if (now.isBefore(timePoint)) {
            upperBound = timePoint;
            lowerBound = timePoint.minusDays(1L);
        } else {
            lowerBound = timePoint;
            upperBound = timePoint.plusDays(1L);
        }
        return src.isAfter(lowerBound) && src.isBefore(upperBound);
    }

    public static boolean isMenuOutDated(LocalDate date, LocalDate now) {
        return now.isAfter(date);
    }

    public static String toString(LocalDateTime localDateTime) {
        return localDateTime != null ?
                localDateTime.format(DateTimeFormatter.ISO_DATE_TIME).replace('T', ' ') : StringUtils.EMPTY;
    }

    public static LocalDate parseStartLocalDate(String startDate) {
        return parseLocalDate(startDate);
    }

    public static LocalDate parseEndLocalDate(String endDate) {
        return parseLocalDate(endDate);
    }

    private static LocalDate parseLocalDate(String sDate) {
        return !StringUtils.isEmpty(sDate) ? LocalDate.parse(sDate, DateTimeFormatter.ISO_LOCAL_DATE) : null;
    }
}
