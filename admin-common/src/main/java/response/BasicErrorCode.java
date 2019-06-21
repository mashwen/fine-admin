package response;

/**
 * Created by lgc on 2017/5/24.
 */
public enum BasicErrorCode {
    /**
     * System undefine exception basic error code.
     */
    SYSTEM_UNDEFINE_EXCEPTION("10000", "系统未知异常"),
    /**
     * Param error basic error code.
     */
    PARAM_ERROR("20002", "参数不正确"),
    /**
     * Member error basic error code.
     */
    MEMBER_ERROR("30000", "会员系统异常");

    private String code;
    private String name;

    BasicErrorCode(String code, String name) {
        this.code = code;
        this.name = name;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
