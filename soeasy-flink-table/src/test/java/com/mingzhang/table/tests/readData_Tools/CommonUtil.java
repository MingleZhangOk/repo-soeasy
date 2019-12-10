package com.mingzhang.table.tests.readData_Tools;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    static {
        Properties properties = new Properties();
        try {
            properties.load(getBasicBipConfig());
        } catch (Exception e) {

        }
    }

    public static Date getFormatDate(String formatType, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        return sdf.parse(date);
    }

    public static Date getTransDate(String formatType, String date, String datetime) {

        String newDate = date + " " + datetime;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatType);
            return sdf.parse(newDate);
        } catch (Exception e) {
            return new Date();
        }

    }


    /**
     * @param formatType
     * @param date
     * @return
     */
    public static String getFormatDateStr(String formatType, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        return sdf.format(date);
    }


    public static String getfixStrFromLong(Long lg, int fixLength) {

        String strLg = String.valueOf(lg);
        int numberLenth = strLg.length();
        if (numberLenth < fixLength) {
            for (int i = 0; i < fixLength - numberLenth; i++) {
                strLg = "0" + strLg;
            }
        } else if (numberLenth > fixLength) {

            strLg = strLg.substring(strLg.length() - fixLength);
        }

        return strLg;
    }

    public static String retNotNullStrVal(Object strData) {
        if (strData == null) {
            return "";
        } else {
            return strData.toString();
        }
    }


    public static String WildcardForEsexpress(Object express) {
        if (express == null) {
            return "";
        } else {
            //假定分隔符为？与%
            String expressStr = express.toString().replaceAll("\\*", "\\\\*");
            expressStr = expressStr.replaceAll("%", "*");
            return expressStr;
        }

    }

    public static boolean MatchLike(String express, String data) {

        if (express == null || express.length() == 0) {
            return true;
        } else {
            //假定分隔符为？与%
            express = express.replaceAll("\\*", "\\\\*");
            express = express.replaceAll("\\.", "\\\\.");
            express = express.replaceAll("%", "[\\\\s\\\\S]*");
            express = express.replaceAll("\\?", ".");

        }


        Pattern pattern = Pattern.compile(express);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }


    public static InputStream getBasicBipConfig() {
        String frmsBasePath = System.getenv(CodeString.ENV_FRMS_PATH);
        InputStream inputStream = null;
        if (frmsBasePath == null) {
            inputStream = CommonUtil.class.getResourceAsStream("/bip.properties");
        } else {
            try {
                inputStream = new FileInputStream(frmsBasePath + "/bip.properties");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return inputStream;
    }
}
