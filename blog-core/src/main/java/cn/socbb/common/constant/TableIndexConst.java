package cn.socbb.common.constant;

/**
 * 表常量, 每张表对应一个序号 该序号参与该表的id构建
 * Created by cbb on 2018/7/15.
 */
public interface TableIndexConst {

    /**
     * 机器序号
     */
    public static final Integer MACHINE_INDEX = 1;


    /**
     * 表序号
     */
    public static final Integer TABLE_USER = 1;
    public static final Integer TABLE_ROLE = 2;
    public static final Integer TABLE_MENU = 3;
    public static final Integer TABLE_USER_ROLE = 4;
    public static final Integer TABLE_ROLE_MENU = 5;
    public static final Integer TABLE_DICT = 6;

}
