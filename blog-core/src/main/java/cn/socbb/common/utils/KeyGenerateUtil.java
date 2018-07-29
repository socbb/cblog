package cn.socbb.common.utils;

import org.apache.commons.collections.CollectionUtils;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by cbb on 2018/7/29.
 */
public class KeyGenerateUtil {

    public static void main(String[] args) {
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < 1000000; i++) {
            long id = id();
            System.out.println(id + " | " + i);
            if (CollectionUtils.isNotEmpty(set) && set.contains(id)) {
                System.out.println("有重复的: " + id);
                return;
            }
            set.add(id);
        }
    }

    public static Long id(){
        byte[] buffer = UUID.randomUUID().toString().replace("-", "").getBytes(Charset.forName("UTF-8"));
        return byteToLong(buffer);
    }

    private static long byteToLong(byte[] b) {
        long s = 0;
        long s0 = b[0] & 0xff;// 最低位
        long s1 = b[1] & 0xff;
        long s2 = b[2] & 0xff;
        long s3 = b[3] & 0xff;
        long s4 = b[4] & 0xff;// 最低位
        long s5 = b[5] & 0xff;
        long s6 = b[6] & 0xff;
        long s7 = b[7] & 0xff;

        // s0不变
        s1 <<= 8;
        s2 <<= 16;
        s3 <<= 24;
        s4 <<= 8 * 4;
        s5 <<= 8 * 5;
        s6 <<= 8 * 6;
        s7 <<= 8 * 7;
        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
        return s;
    }

}
