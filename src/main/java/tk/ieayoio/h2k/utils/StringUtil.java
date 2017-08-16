package tk.ieayoio.h2k.utils;

/**
 * Created by ieayoio on 2017/7/26.
 */
public class StringUtil {

    public static boolean isBank(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(isBank(null));
        System.out.println(isBank(""));
        System.out.println(isBank("   "));
        System.out.println(isBank("sdf"));
        System.out.println(isBank(" s dfsdf "));
    }
}
