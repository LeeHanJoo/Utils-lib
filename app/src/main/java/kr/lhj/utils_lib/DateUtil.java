package kr.lhj.utils_lib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by user on 2017-04-07.
 */

public class DateUtil  {

    /**
     * 현재 date type 을 원하는 type 으로 변경
     * @param datetime
     * @param thistype
     * @param type
     * @return 예) 20130410 > 2013년04월10일
     */
    public String getTimeEncoding(String datetime,String thistype ,String type) {
        SimpleDateFormat format = new java.text.SimpleDateFormat(thistype);
        Date date = null;
        try {
            date = format.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format1 = new java.text.SimpleDateFormat(type);
        String dateString = format1.format(date);
        return dateString;

    }

    /**
     * Calendar.get(Calendar.dayofWeek)를 넣으면 해당 요일을 반환
     * @param week
     * @return 예) GetMonthOfWeek(cal.get(Calendar.DAY_OF_WEEK));
     */
    public String GetMonthOfWeek(int week) {
        String monthof = "";
        switch (week) {
            case 1:
                monthof = "일요일";
                break;
            case 2:
                monthof = "월요일";
                break;
            case 3:
                monthof = "화요일";
                break;
            case 4:
                monthof = "수요일";
                break;
            case 5:
                monthof = "목요일";
                break;
            case 6:
                monthof = "금요일";
                break;
            case 7:
                monthof = "토요일";
                break;
        }
        return monthof;
    }

    /**
     * 날짜가 한자리일때 앞에 0을 붙여줌
     *
     * @return 예) 6 > 06
     */
    public static String returnZero(int days) {
        String day = String.format("%02d",days);
        return day;
    }

    /**
     * d오늘날짜구하기
     * @return
     */
    public static String getToday() {
        Calendar today = Calendar.getInstance();
        int inmonth = today.get(Calendar.MONTH) + 1;
        int inDay = today.get(Calendar.DAY_OF_MONTH);
        int inyear = today.get(Calendar.YEAR);

        return Integer.toString(inyear) + returnZero(inmonth)+ returnZero(inDay);
    }

    public final static int SEC 	= 60;
    public final static int MIN 	= 60;
    public final static int HOUR 	= 24;
    public final static int DAY 	= 7;
    public final static int MONTH = 12;

    /**
     * x분전, x시간전, x일전 으로 변환
     * @param dataString
     * @return
     */
    public static String createDataWithCheck(String dataString)
    {
        //		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
        //				"yyyy-MM-dd HH:mm:ss.S");
        java.text.SimpleDateFormat format= null;
        if(dataString.length() != 21) {
            format = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        }
        else {
            format = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH);
        }
        java.util.Date date = null;
        try {
            date = format.parse(dataString);

            long curTime = System.currentTimeMillis();
            long regTime = date.getTime();
            long diffTime = (curTime - regTime) / 1000;

            String msg = null;
            if (diffTime < SEC) {
                // sec

                SimpleDateFormat aformat = new SimpleDateFormat("aa HH:mm");
                //msg = aformat.format(date);
                String time = aformat.format(date);
                String[] entime = time.split(" ");
                String[] entime2 = entime[1].split(":");
                if(Integer.valueOf(entime2[0]) > 12){
                    msg =  entime[0] +" " + String.valueOf(Integer.valueOf(entime2[0]) -12) + ":" + entime2[1];
                }else{
                    msg = aformat.format(date);
                }
                msg = "방금 전";
            } else if ((diffTime /= SEC) < MIN) {
                // min
                SimpleDateFormat aformat = new SimpleDateFormat("aa HH:mm");
                //msg = aformat.format(date);
                String time = aformat.format(date);
                String[] entime = time.split(" ");
                String[] entime2 = entime[1].split(":");
                if(Integer.valueOf(entime2[0]) > 12){
                    msg =  entime[0] +" " + String.valueOf(Integer.valueOf(entime2[0]) -12) + ":" + entime2[1];
                }else{
                    msg = aformat.format(date);
                }
                msg = diffTime + "분 전";
            } else if ((diffTime /= MIN) < HOUR) {
                // hour
                SimpleDateFormat aformat = new SimpleDateFormat("aa HH:mm");
                //msg = aformat.format(date);
                String time = aformat.format(date);
                String[] entime = time.split(" ");
                String[] entime2 = entime[1].split(":");
                if(Integer.valueOf(entime2[0]) > 12){
                    msg =  entime[0] +" " + String.valueOf(Integer.valueOf(entime2[0]) -12) + ":" + entime2[1];
                }else{
                    msg = aformat.format(date);
                }
                msg = (diffTime) + "시간 전";
            } else if ((diffTime /= HOUR) < DAY) {
                // day
                SimpleDateFormat aformat = new SimpleDateFormat("MM월dd일");
                msg = aformat.format(date);

                msg = (diffTime) + "일 전";
            }
            else {
                SimpleDateFormat aformat = new SimpleDateFormat("MM월dd일");
                msg = aformat.format(date);
            }
            return msg;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
