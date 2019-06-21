package response;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by mac on 16/12/8.
 */
public class BasicResponse implements Serializable {

    /**
     * 状态, 0-失败,1-成功.
     */
    private String status;
    /**
     * 错误码.
     */
    private String errorCode;
    /**
     * 错误信息描述.
     */
    private String errorMessage;
    /**
     * 实际返回信息.
     */
    private Map data;

    /**
     * Instantiates a new Basic response.
     */
    public BasicResponse() {
    }

    /**
     * Instantiates a new Basic response.
     *
     * @param status       the status
     * @param errorCode    the error code
     * @param errorMessage the error message
     * @param data         the data
     */
    public BasicResponse(String status, String errorCode, String errorMessage, Map data) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    /**
     * Error basic response.
     *
     * @param errorCode the error code
     * @return the basic response
     */
    public static BasicResponse error(BasicErrorCode errorCode) {
        return new BasicResponse(BasicStatus.RESPONES_FAIL.getCode(), errorCode.getCode(), errorCode.getName(), null);
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets error code.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets error code.
     *
     * @param errorCode the error code
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Map getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Map data) {
        this.data = data;
    }
}
