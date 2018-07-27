package cn.socbb.common.utils;

/**
 * Created by cbb on 2018/7/15.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 全角转半角
     * @param value
     * @return
     */
    public static String full2Half(String value) {
        if (value == null) {
            return null;
        }
        char[] cc = value.toCharArray();
        for (int i = 0; i < cc.length; i++) {
            if (cc[i] == 12288) {
                // 表示空格
                cc[i] = (char) 32;
                continue;
            }
            if (cc[i] > 65280 && cc[i] < 65375) {
                cc[i] = (char) (cc[i] - 65248);
            }
        }
        return new String(cc);
    }

}
