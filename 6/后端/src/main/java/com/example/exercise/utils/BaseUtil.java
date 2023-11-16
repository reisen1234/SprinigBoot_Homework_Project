package com.example.exercise.utils;

import com.example.exercise.domain.pojo.TbUser;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BaseUtil {
    public static Timestamp getCurrentTime(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return timestamp;
    }
    public static boolean check(TbUser user){
        if((user.getName() == null && user.getName().equals("") ) || (user.getPassword() == null && user.getPassword().equals("")))
            return false;
        return true;
    }
}
