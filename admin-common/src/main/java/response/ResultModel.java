package response;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义返回结果
 *
 * @author XieEnlong
 * @date 2015 /7/14.
 */
public class ResultModel {

    /**
     * 返回码.
     */
    private String code;

    /**
     * 返回结果描述.
     */
    private String message;

    /**
     * 返回内容.
     */
    private Map content;

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public Map getContent() {
        return content;
    }

    /**
     * 构造器1.
     *
     * @param code    the code
     * @param message the message
     */
    public ResultModel(String code, String message) {
        this.code = code;
        this.message = message;
        this.content = new HashMap();
    }

    /**
     * 构造器2.
     *
     * @param code    the code
     * @param message the message
     * @param content the content
     */
    public ResultModel(String code, String message, Map content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    /**
     * 构造器3.
     *
     * @param status the status
     */
    public ResultModel(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = new HashMap();
    }

    /**
     * 构造器4.
     *
     * @param status  the status
     * @param content the content
     */
    public ResultModel(ResultStatus status, Map content) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = content;
    }



    /**
     * 有返回消息的正确响应.
     *
     * @param content the content
     * @return result model
     */
    public static ResultModel ok(Map content) {
        return new ResultModel(ResultStatus.SUCCESS, content);
    }

    /**
     * 正确响应.
     *
     * @return result model
     */
    public static ResultModel ok() {
        return new ResultModel(ResultStatus.SUCCESS);
    }

    /**
     * 枚举中错误.
     *
     * @param error the error
     * @return result model
     */
    public static ResultModel error(ResultStatus error) {
        return new ResultModel(error);
    }

    /**
     * 错误.
     *
     * @param code    the code
     * @param message the message
     * @return result model
     */
    public static ResultModel error(String code, String message) {
        return new ResultModel(code, message);
    }

    /**
     * 返回异常提示信息
     * @param message
     * @return
     */
    public static ResultModel error(String message){
        return new ResultModel(ResultStatus.ERROR.getCode(),message);
    }

    /**
     * 将会员数据原样返回.
     *
     * @param basicResponse the basic response
     * @return result model
     */
    public static ResultModel basicResponse(BasicResponse basicResponse) {
        if (BasicStatus.RESPONES_SUCCESS.getCode().equals(basicResponse.getStatus())) {
            if (basicResponse.getData() != null) {
                return ResultModel.ok(basicResponse.getData());
            } else {
                return ResultModel.ok();
            }
        } else {
            return ResultModel.error(basicResponse.getErrorCode(), basicResponse.getErrorMessage());
        }
    }

    /**
     * content中不返回对应结果.
     *
     * @param basicResponse the basic response
     * @return result model
     */
    public static ResultModel basicResponseWithoutData(BasicResponse basicResponse) {
        if (BasicStatus.RESPONES_SUCCESS.getCode().equals(basicResponse.getStatus())) {
            return ResultModel.ok();
        } else {
            return ResultModel.error(basicResponse.getErrorCode(), basicResponse.getErrorMessage());
        }
    }
}
