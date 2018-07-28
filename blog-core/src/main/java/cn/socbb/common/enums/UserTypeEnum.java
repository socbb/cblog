package cn.socbb.common.enums;

import org.apache.commons.lang3.StringUtils;

public enum UserTypeEnum {
    ROOT("超级管理员"),
    ADMIN("后台用户"),
    USER("前台用户"),
    UNKNOW("未知");
    private String desc;

    UserTypeEnum(String desc) {
        this.desc = desc;
    }

    public static UserTypeEnum getByType(String type) {
        if (StringUtils.isEmpty(type)) {
            return UserTypeEnum.UNKNOW;
        }
        for (UserTypeEnum ut : UserTypeEnum.values()) {
            if (ut.toString().equalsIgnoreCase(type)) {
                return ut;
            }
        }
        return UserTypeEnum.UNKNOW;
    }

    public String getDesc() {
        return desc;
    }
}
