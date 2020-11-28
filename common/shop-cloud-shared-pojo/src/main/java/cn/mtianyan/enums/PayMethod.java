package cn.mtianyan.enums;

/**
 * @Description: 支付方式 枚举
 * Create By mtianyan
 * 2020/7/18 02:10
 */
public enum PayMethod {

    WEIXIN(1, "微信"),
    ALIPAY(2, "支付宝");

    public final Integer type;
    public final String value;

    PayMethod(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

}
