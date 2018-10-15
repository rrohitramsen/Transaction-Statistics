package com.n26.helper;

import com.n26.exception.FieldValidatorException;
import org.springframework.http.HttpStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author rohitkumar
 */
public abstract class DateUtils {

    public static Date parseDate(String timeStamp) {

        SimpleDateFormat dateFormat = getDateFormatter(timeStamp);
        try {
            return dateFormat.parse(timeStamp);
        } catch (ParseException e) {
            throw new FieldValidatorException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private static SimpleDateFormat getDateFormatter(String timeStamp) {

        int dateLen = timeStamp.length();
        SimpleDateFormat dateFormat;
        if(dateLen <= 20) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        }else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        }
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat;
    }
}
