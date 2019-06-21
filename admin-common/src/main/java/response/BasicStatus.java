package response;

/**
 * Created by mac on 16/12/8.
 */
public enum BasicStatus {

    /**
     * 响应失败.
     */
    RESPONES_FAIL("0"),
    /**
     * 响应成功.
     */
    RESPONES_SUCCESS("1");

    private String code;

    BasicStatus(String code) {
        this.code = code;
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
