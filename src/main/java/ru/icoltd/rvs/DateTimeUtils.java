package ru.icoltd.rvs;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    private final static LocalTime TIME_BOUND = LocalTime.of(11, 0);

    public final static ZoneId ZONE_ID_UTC = ZoneId.of("UTC");

    // DataBase doesn't support LocalDate.MIN/MAX
    public static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);

    public static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

    private DateTimeUtils() {
    }

    public static boolean isBetween(LocalDateTime src, LocalDateTime now) {
        LocalDateTime lowerBound = LocalDateTime.of(now.toLocalDate(), TIME_BOUND);
        LocalDateTime upperBound = lowerBound.plusDays(1);
        return src.isAfter(lowerBound) && src.isBefore(upperBound);
    }

    public static boolean isNotBeforeNow(ZonedDateTime zonedDateTime, LocalDateTime now) {
        LocalDate currentDate = now.toLocalDate();
        LocalDate menuPublishedDate = zonedDateTime.toLocalDate();
        return currentDate.isBefore(menuPublishedDate) || currentDate.isEqual(menuPublishedDate);
    }

    public static String toString(ZonedDateTime date) {
        return date != null ? date.format(DateTimeFormatter.ISO_LOCAL_DATE) : StringUtils.EMPTY;
    }

    public static ZonedDateTime parseStartZoneDateTime(String startDate) {
        return parseZoneDateTime(startDate, LocalTime.MIN);
    }

    public static ZonedDateTime parseEndZoneDateTime(String endDate) {
        return parseZoneDateTime(endDate, LocalTime.MAX);
    }

    private static ZonedDateTime parseZoneDateTime(String sDate, LocalTime localTime) {
        return !StringUtils.isEmpty(sDate) ?
                ZonedDateTime.of(
                        LocalDate.parse(sDate, DateTimeFormatter.ISO_LOCAL_DATE), localTime, ZONE_ID_UTC
                ) : null;
    }
}
