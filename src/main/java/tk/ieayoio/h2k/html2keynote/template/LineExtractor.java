package tk.ieayoio.h2k.html2keynote.template;

/**
 * Created by ieayoio on 2017/7/13.
 */
public class LineExtractor {

    /**
     * 获得范围内从子串
     *
     * @param s
     * @param range
     * @return
     */
    public static String fetch(String s, Range range) {

        return s.substring(range.getStart(), range.getEnd());
    }

    /**
     * 替换一个范围的字符串
     *
     * @param s
     * @param range
     * @param replaceString
     * @return
     */
    public static String replace(String s, Range range, String replaceString) {
        int rangeStart = range.getStart();
        int rangeEnd = range.getEnd();
        int length = s.length();

        int s1 = 0, e1, s2, e2 = 0;

        e1 = rangeStart > length ? length : rangeStart;

        s2 = e1 + range.getLength() > length ? length : e1 + range.getLength();
        e2 = length;


        return s.substring(s1, e1) + replaceString + s.substring(s2, e2);
    }

    /**
     * 查找从某一下标开始最后一个满足规则的下标
     *
     * @param s
     * @param index
     * @param direction 查找方向 0 向左，1 向右
     * @param charRule
     * @return
     */
    private static int find(String s, int index, int direction, CharRule charRule) {

        if (direction == 0) {
            int reIndex = -1;
            for (int i = index; i >= 0; i--) {
                if (!charRule.expect(s.charAt(i))) {
                    reIndex = i;
                    break;
                }
            }
            return reIndex + 1;
        } else {
            int reIndex = s.length();
            for (int i = index; i < s.length(); i++) {
                if (!charRule.expect(s.charAt(i))) {
                    reIndex = i;
                    break;
                }
            }
            return reIndex - 1;
        }
    }


    /**
     * 从不同处查找参数范围
     *
     * @param s
     * @param alienIndex
     * @return
     */
    public static Range findParam(String s, int alienIndex) {

        CharRule charRule = new CharRule() {
            @Override
            public boolean expect(char c) {
                return c >= '0' && c <= '9';
            }
        };

        if (!charRule.expect(s.charAt(alienIndex))) {
            return null;
        }


        int start = find(s, alienIndex, 0, charRule);
        int end = find(s, alienIndex, 1, charRule);
        return new Range(start, end + 1);
    }

    /**
     * 从不同处查找参数名称范围
     * @param s
     * @param alienIndex
     * @return
     */
    public static Range findName(String s, int alienIndex) {
        int end = find(s, alienIndex, 0, new CharRule() {
            @Override
            public boolean expect(char c) {
                boolean isUpper = c >= 'A' && c <= 'Z';
                boolean isLowercase = c >= 'a' && c <= 'z';
                return !isUpper && !isLowercase;
            }
        });

        if (end <= 0) {
            return null;
        }

        int start = find(s, end - 1, 0, new CharRule() {
            @Override
            public boolean expect(char c) {
                boolean isUpper = c >= 'A' && c <= 'Z';
                boolean isLowercase = c >= 'a' && c <= 'z';
                return isUpper || isLowercase || c == '-';
            }
        });

        return new Range(start, end);

    }

    public static void main(String[] args) {
        System.out.println(replace("01234567", new Range(4, 5), "-------"));


        String ert1234 = "12:er---t1234hf";

        System.out.println(ert1234);

        System.out.println(fetch(ert1234, findParam(ert1234, 10)));

        System.out.println(fetch(ert1234, findName(ert1234, 10)));

        System.out.println(replace(ert1234, findParam(ert1234, 10),"====="));
    }

}
