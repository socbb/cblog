package cn.socbb.common.enums;

/**
 * Created by cbb on 2018/7/16.
 */
public enum MenuTypeEnum {

    MENU(1, "菜单"),
    BUTTON(2, "按钮"),;
    private Integer code;
    private String desc;

    MenuTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static MenuTypeEnum get(Integer code) {
        MenuTypeEnum[] enums = MenuTypeEnum.values();
        for (MenuTypeEnum anEnum : enums) {
            if (anEnum.getCode().equals(code)) {
                return anEnum;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
