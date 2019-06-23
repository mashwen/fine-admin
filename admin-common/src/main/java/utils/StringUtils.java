package utils;

import java.util.regex.Pattern;

/**
 * @Author liuzongqiang
 * @Date 2019/6/23 0023 18:26
 * @Version 1.0
 **/
public class StringUtils {

    private static Pattern PATTERN = Pattern.compile("^[-\\+]?[\\d]*$");

    /*
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        return PATTERN.matcher(str).matches();
    }
}
