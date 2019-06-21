package response;

/**
 * 自定义请求状态码
 *
 * @author ScienJus
 * @date 2015/7/15.
 */
public enum ResultStatus {
    SUCCESS("1", "成功"),
    ERROR("0", "系统异常"),
    PARAM_ERROR("-1", "参数不合法"),
    USERNAME_OR_PASSWORD_ERROR("-10000", "用户名或密码错误"),
    REGISTER_IDENTIFY_ERROR("-10001", "注册验证码错误"),
    REGISTER_IDENTIFY_AGAIN("-10002", "请重新发送验证码"),
    USER_LOCK_ERROR("-10003", "该帐号已经被锁定"),
    IDENTIFY_CODE_EXCEPTION("-10004", "您多次验证码操作异常,该手机号已被锁定"),
    USER_MOBILE_OR_CODE_ERROR("-10005", "手机号或验证码错误"),
    USER_PASSWORD_ERROR("-10006", "密码错误"),
    REGISTER_IDENTIFY_TIME_OUT("-10007", "短信验证码已过期"),
    USER_OLD_PASSWORD_ERROR("-10008", "原密码错误"),
    IDENTIFY_ERROR("-10009", "验证码错误"),
    USER_BLACK_LOGIN_ERROR("-10010", "此账号禁止登录"),
    USER_BLACK_COMMENT_ERROR("-10011", "此账号已禁止评论"),
    USER_MOBILE_ERROR("-10012", "手机号格式不正确"),
    USER_NOT_EXIST("-10013", "用户不存在"),
    SMS_SIGNATURE_ERROR("-10014", "短信签名错误"),
    SSM_SEND_TIME_ERROR("-20000", "短信发送频率过高,请稍后再试"),
    SSM_SEND_SERVICE_ERROR("-20001", "短信网关错误"),
    SSM_SEND_OVER_DAY_LIMIT("-20002", "短信发送次数超出当日上限"),
    CINEMA_NULL_ERROR("-30000", "无影城信息"),
    CARD_INFO_ERROR("-40000", "会员卡信息获取失败"),
    CARD_CAN_RECHARGE_ERROR("-40001", "此会员卡无法充值"),
    CARD_RECHARGE_PACKAGE_NULL_ERROR("-40002", "此充值套餐不存在或者已下架"),
    CARD_RECHARGE_PACKAGE_MONEY_ERROR("-40003", "支付金额与套餐不匹配"),
    RIGHT_EXIST_ERROR("-40004", "用户已订购此权益"),
    CARD_UNBIND_ERROR("-40005", "卡解绑异常"),
    CARD_RECHARGE_LIMIT_ERROR("-40006", "抱歉，该充值档位与您的卡等级不符，请选择其他充值档位。"),
    CARD_RECHARGE_CINEMA_DIF_ERROR("-40007", "抱歉，目前暂不支持跨影城充值。"),
    CARD_RECHARGE_CINEMA_ERROR("-40008", "影城不存在！"),
    CARD_ORDER_STATUS_ERROR("-40009", "无法获取支付链接，订单状态异常。"),
    VOUCHER_BIND_FAIL("-50001", "无法绑定此优惠券"),
    VOUCHER_PARAM_ERROR("-50002", "优惠券格式不正确"),
    SENSITIVE_WORDS_FAIL("-50003", "您输入的内容含有敏感词汇！"),
    COMMENTS_NULL_FAIL("-50004", "评论内容不允许为空！"),
    ACTIVITY_EXIST_FAIL("-50005", "活动不存在！"),
    ACTIVITY_JOIN_DUPLICATE("-50006", "您已经参与过本活动，不能重复参与！"),
    ACTIVITY_JOIN_FAIL("-50007", "抢券失败！"),
    ACTIVITY_OVER("-50008", "活动已经结束！"),
    EQUITIES_THIRD_PARTY_MERCHANT_ORDER_ID_DUPLICATE("-60001", "商户订单号重复"),
    EQUITIES_THIRD_PARTY_NOT_FUND("-60002", "没有此权益卡"),
    EQUITIES_THIRD_PARTY_NOT_SALEABLE("-60003", "此权益卡目前不可销售"),
    EQUITIES_THIRD_PARTY_UNBIND_NOT_SALEABLE("-60004", "解绑失败，没有绑定记录"),
    EQUITIES_THIRD_PARTY_UNBIND_OUT_OF_DATE("-60005", "解绑失败，权益卡已经过期"),
    EQUITIES_THIRD_PARTY_ORDER_ID_TOO_LONG("-60006", "商户订单号长度应在50位以内"),
    DERIVATIVE_NOT_EXSIT("-70001", "商品不存在"),
    DERIVATIVE_INVENTORY_SHOT("-70002", "库存不足"),
    DERIVATIVE_UNPAY_ORDER_EXSIT("-70003", "已有同一商品在待支付订单中"),
    DERIVATIVE_ORDER_NOT_FOUND("-70004", "订单不存在"),
    DERIVATIVE_ORDER_OVER("-70005", "超过每人限购数量"),
    CARD_PAY_PASSWORD_ERROR("-80001", "支付密码错误，请确认后输入<br>进入“我的”页面点击头像<br>“修改支付密码”可重置<br>连续错误3次后您的账户将被锁定"),
    CARD_PAY_PASSWORD_SET_ERROR("-80002", "支付密码只能是六位数字"),
    CARD_PAY_LOCK_ERROR("-80003", "支付密码输入错误过多账户已被锁定，请3小时后重试");
    /**
     * 返回码.
     */
    private String code;

    /**
     * 返回结果描述.
     */
    private String message;

    ResultStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
