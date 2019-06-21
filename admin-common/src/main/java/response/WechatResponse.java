package response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class WechatResponse {

    //  SUCCESS/FAIL
    //SUCCESS表示商户接收通知成功并校验成功
    private String return_code;
    //  返回信息，如非空，为错误原因：
    //签名失败
    //参数格式校验错误
    private String return_msg;

    public WechatResponse() {
    }

    public WechatResponse(String returnCode, String returnMsg) {
        this.return_code = returnCode;
        this.return_msg = returnMsg;
    }

    @XmlElement
    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String returnCode) {
        this.return_code = returnCode;
    }

    @XmlElement
    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String returnMsg) {
        this.return_msg = returnMsg;
    }


}
