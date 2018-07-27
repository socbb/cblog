package cn.socbb.common.enums;

/**
 * Created by cbb on 2018/7/27.
 */
public enum  BooleanEnmu {

    YES(1, "是"),
    NO(0, "否"),;
    private Integer code;
    private String desc;

    BooleanEnmu(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static BooleanEnmu get(Integer code) {
        BooleanEnmu[] enums = BooleanEnmu.values();
        for (BooleanEnmu anEnum : enums) {
            if (anEnum.getCode().equals(code)) {
                return anEnum;
            }
        }
        return YES;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
