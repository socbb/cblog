package cn.socbb.common.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 主键ID 构建方式: 序号(三位) +
 * Created by cbb on 2018/7/15.
 */
public class IdUtils {

    public static synchronized Long get(Integer tableIndex) {
        String format = DateUtils.YYMMDDHHMMSSSSSS.format(new Date());
        String s = format + String.format("%0" + 2 + "d", tableIndex);
        return Long.valueOf(s);
    }

    public static void main(String[] args) {
        Set<Long> set = new HashSet<>();
        for (int i = 1; i <= 100000; i++) {
            Long aLong = get(1);
            System.out.println(aLong + " | " + i);
            if (CollectionUtils.isNotEmpty(set) && set.contains(aLong)) {
                System.out.println("有重复的: " + aLong);
                return;
            }
            set.add(aLong);
        }
    }
}
