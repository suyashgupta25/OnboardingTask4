package de.appsfactory.userportal.util;

import lombok.NonNull;

import java.util.Date;

public class DateUtil {

    public static final String LOCAL_DATE_FORMAT = "MM/dd/yyyy";

    public static boolean isInThePast(@NonNull Date value){
        return value.before(new Date());
    }
}
