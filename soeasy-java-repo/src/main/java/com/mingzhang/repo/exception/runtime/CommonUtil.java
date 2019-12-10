package com.mingzhang.repo.exception.runtime;



import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    public static String getLoggerMessage(String message,Object...param){
        Matcher matcher = Pattern.compile(CodeString.LOG_PATTERN_VARABLE).matcher(message);
        int currentIndex= 0;
        while(matcher.find()&&currentIndex<param.length){
            message= message.replaceFirst(CodeString.LOG_PATTERN_VARABLE,param[currentIndex++].toString());
        }
        return message;
    }

    public static String getFullFileName(String path,String fileName){
        String retFullName = "";
        if(path.endsWith("/")){
            retFullName = path + fileName;
        }else{
            retFullName = path + "/" +fileName;
        }

        return retFullName;
    }

    public static String getPreFileName(String fileName){
        return fileName.split("\\.")[0];
    }


    public static String[] convertStrToArray(String strData,char spliteChar){
        if(strData!=null){
            if (spliteChar == '|') {
                return strData.split("\\|");
            }else{
                return strData.split( String.valueOf(spliteChar));
            }
            }

            return null;

        }


    public static String convertArrayoSqlConidtion(String...  datas){

        if(datas==null||datas.length==0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<datas.length;i++){
            sb.append(",'").append(datas[i]).append("'");
        }
        return sb.toString().substring(1);
    }




    public  static  <T extends Object>   String  convertListToSqlConidtion(List<T> list){

        if(list==null||list.size()==0){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        try {
            list.forEach(data -> {
                if(String.class.equals(data.getClass()) ){
                    sb.append(",'").append(data).append("'");
                }else{
                    sb.append(",").append(data);
                }
            } );
        }catch (Exception e){
        }
        return sb.toString().substring(1);
    }

    public static String replaceDateFormatExpress(String expressVal,String date){
        String ddStr="";
        String  mmstr ="";
        String yearStr = "";
        String hours = "";
        String minites = "";
        if(!StringUtils.isEmpty(date)){
            if(date.length()>=4){
                yearStr = date.substring(0,4);
                expressVal = replaceDateExpress(expressVal, DatePatternType.YEAR.getPatternCode(),yearStr);
            }

            if(date.length()>=6){
                mmstr = date.substring(4,6);
                expressVal = replaceDateExpress(expressVal, DatePatternType.MONTH.getPatternCode(),mmstr);
            }

            if(date.length()>=8){
                ddStr = date.substring(6,8);
                expressVal = replaceDateExpress(expressVal, DatePatternType.DATE.getPatternCode(),ddStr);
            }

            if(date.length()>=10){
                hours =  date.substring(8,10);
                expressVal = replaceDateExpress(expressVal, DatePatternType.HOUR.getPatternCode(),hours);
            }

            if(date.length()>=12){
                minites = date.substring(10,12);
                expressVal = replaceDateExpress(expressVal, DatePatternType.MINITES.getPatternCode(),minites);
            }

        }



        return expressVal;
    }

    private static  String replaceDateExpress(String expressStr,String expressType,String value){
        String pattern = Pattern.quote(expressType);
        Pattern pr = Pattern.compile(pattern);

        Matcher matcher = pr.matcher(expressStr);
        if(matcher.find()){
            String express =  matcher.group(0);
            expressStr =  expressStr.replace(express,value);
        }
        return expressStr;
    }

    public static void main(String[] args) {
        String fileName = getPreFileName("/asd/adasd/abc.txt");
        System.err.println(fileName);
    }

}
