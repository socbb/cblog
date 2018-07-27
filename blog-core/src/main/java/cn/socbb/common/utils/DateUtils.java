package cn.socbb.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cbb on 2018/7/15.
 */
public class DateUtils {

    public static final DateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat YYMMDDHHMMSSSSSS = new SimpleDateFormat("yyMMddHHmmssSSSSS");
    public static final DateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parse(String text){
        Date parse = null;
        try {
            parse = DateUtils.YYYYMMDDHHMMSS.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
