package com.example.exercise.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BaseUtil {
    public static Timestamp getCurrentTime(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return timestamp;
    }
}
