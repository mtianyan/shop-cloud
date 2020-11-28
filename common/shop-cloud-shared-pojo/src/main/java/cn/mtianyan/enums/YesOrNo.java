package cn.mtianyan.enums;

/**
 * @Desc: 是否 枚举
 * Create By mtianyan
 * 2019/12/27 09:42
 */
public enum YesOrNo {
    NO(0, "否"),
    YES(1, "是");

    public final Integer type;
    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}

