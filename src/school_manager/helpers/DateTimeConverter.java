/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import school_manager.model.schedule.Schedule;

/**

 @author abrasha
 */
public class DateTimeConverter {

    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String format(LocalDate date){
        if (date == null) {
            return null;
        } else {
            return DATE_FORMATTER.format(date);
        }
    }

    /**

     @param dateString
     @return
     */
    public static LocalDate parse(String dateString){
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean validDate(String dateString){
        return (parse(dateString) != null);
    }

    public static Schedule.Day parseDayOfWeek(int index){
        Schedule.Day result = Schedule.Day.UNKNOWN;

        switch (index) {

            case 0:
                result = Schedule.Day.MONDAY;
                break;
            case 1:
                result = Schedule.Day.TUESDAY;
                break;
            case 2:
                result = Schedule.Day.WEDNESDAY;
                break;
            case 3:
                result = Schedule.Day.THURSDAY;
                break;
            case 4:
                result = Schedule.Day.FRIDAY;
                break;
            case 5:
                result = Schedule.Day.SATURDAY;
                break;
            case 6:
                result = Schedule.Day.SUNDAY;
                break;

        }

        return result;
    }

}
