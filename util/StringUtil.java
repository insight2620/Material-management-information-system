package store_ma.util;


public class StringUtil {

    public static boolean isEmpty(String str){
        if("".equals(str) || str == null){
            return true;
        }
        return false;
    }
}
