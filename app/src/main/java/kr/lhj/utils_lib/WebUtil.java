package kr.lhj.utils_lib;

import org.json.JSONObject;

/**
 * Created by user on 2017-04-07.
 */

public class WebUtil {

    public static String getJsonString(JSONObject jsonObj, String key){
        String result = "";
        try {
            result = jsonObj.getString(key);
        }catch (Exception e){
            e.printStackTrace();
        }

        return result ;
    }

    public static String ReplaceTag(String Expression, String type){
        String result = "";
        if (Expression==null || Expression.equals("")) return "";

        if (type == "encode") {
            result = ReplaceString(Expression, "&","&amp;");
            result = ReplaceString(result, "\"", "&quot;");

            result = ReplaceString(result, "'", "&apos;");
            result = ReplaceString(result, "<", "&lt;");
            result = ReplaceString(result, ">", "&gt;");
            result = ReplaceString(result, "\r", "<br>");
            result = ReplaceString(result, "\n", "<p>");
            result = ReplaceString(result, "\n", "\\(");
            result = ReplaceString(result, "\n", "<p>");
        }
        else if (type == "decode") {
            result = ReplaceString(Expression,"&amp;", "&");
            result = ReplaceString(result, "&quot;", "\"");

            result = ReplaceString(result, "&apos;", "'");
            result = ReplaceString(result, "&lt;", "<");
            result = ReplaceString(result, "&gt;", ">");
            result = ReplaceString(result, "<br>", "\r");
            result = ReplaceString(result, "<p>", "\n");
        }

        return result;
    }

    public static String ReplaceString(String Expression, String Pattern, String Rep)
    {
        if (Expression==null || Expression.equals("")) return "";

        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();

        while ((e = Expression.indexOf(Pattern, s)) >= 0) {
            result.append(Expression.substring(s, e));
            result.append(Rep);
            s = e + Pattern.length();
        }
        result.append(Expression.substring(s));
        return result.toString();
    }

    public static String cleanXSS(String value) {

        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        //value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "&#41;");

        value = value.replaceAll("'", "&#39;");

        value = value.replaceAll("eval\\((.*)\\)", "");

        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

        value = value.replaceAll("script", "");

        return value;

    }

    public static String getSmartEditXSS(String value){

        //String contentstag = ReplaceTag(value, "decode"); // 치환
        String contents = cleanXSS(value); // script 삭제
        //contents = ReplaceTag(contents, "encode"); // 치환
        return contents;
    }
}
