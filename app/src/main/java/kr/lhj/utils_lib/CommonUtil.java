package kr.lhj.utils_lib;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 2017-04-07.
 */

public class CommonUtil {

    /**
     *  String 인코딩타입으로 변경
     * @param value
     * @param encodeType
     * @return
     */
    public static String stringEncoding(String value,String encodeType){
        String encoding = null;
        try {
            encoding = java.net.URLEncoder.encode(new String(value.getBytes(encodeType)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoding;
    }

    /**
     *  휴대폰번호 유효성 체크
     * @param phoneNumber
     * @return
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        boolean returnValue = false;
        boolean returnValue010 = false;
        boolean returnValue01x = false;

        String regex010 = "^\\s*(010)(|\\)|\\s)*(\\d{4})(|\\s)*(\\d{4})\\s*$";
        Pattern p010 = Pattern.compile(regex010);
        Matcher m010 = p010.matcher(phoneNumber);
        if (m010.matches()) {
            returnValue010 = true;
        }

        String regex01x = "^\\s*(011|016|017|018|019)(|\\)|\\s)*(\\d{3})(|\\s)*(\\d{4})\\s*$";
        Pattern p01x = Pattern.compile(regex01x);
        Matcher m01x = p01x.matcher(phoneNumber);
        if (m01x.matches()) {
            returnValue01x = true;
        }

        if(returnValue010 || returnValue01x){
            returnValue = true;
        }

        return returnValue;
    }

    /**
     * 이메일유효성 체크
     * @param email
     * @return
     */
    public boolean isValidEmail(String email){
        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        boolean isNormal = m.matches();
        return isNormal;
    }

    /**
     * 숫자 천자리마다 콤마찍기
     * @param str
     * @return
     */
    public String makeStringComma(String str) {
        if (str.length() == 0)
            return "";
        long value = Long.parseLong(str);
        DecimalFormat format = new DecimalFormat("###,###");
        return format.format(value);
    }

    private static final char[] KEY_LIST = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z' };

    private static Random rnd = new Random();
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private static String getRandomStr() {
        char[] rchar = { KEY_LIST[rnd.nextInt(36)], KEY_LIST[rnd.nextInt(36)],
                KEY_LIST[rnd.nextInt(36)], KEY_LIST[rnd.nextInt(36)],
                KEY_LIST[rnd.nextInt(36)], KEY_LIST[rnd.nextInt(36)], KEY_LIST[rnd.nextInt(36)]
                , KEY_LIST[rnd.nextInt(36)] };
        return String.copyValueOf(rchar);
    }

    /**
     * random 파일명 만들기
     * @return
     */
    public static String getFileKey() {
        return getRandomStr()
                + dateFormat.format(new Date(System.currentTimeMillis()));
    }

}
