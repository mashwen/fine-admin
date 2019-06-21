package response;

/**
 * 券系统调用返回结果
 * Created by lgc on 2017/2/10.
 */
public class MessageResponseInterface {
    private String retcode = "0"; // 0.SUCCESS; 1.FAIL; 2.EXCEPTION;
    private Object retmsg = null;

    /**
     * Instantiates a new Message response interface.
     */
    public MessageResponseInterface() {
    }

    /**
     * Instantiates a new Message response interface.
     *
     * @param retcode the retcode
     * @param retmsg  the retmsg
     */
    public MessageResponseInterface(String retcode, Object retmsg) {
        this.retmsg = retmsg;
        this.retcode = retcode;
    }

    /**
     * Gets retcode.
     *
     * @return the retcode
     */
    public String getRetcode() {
        return retcode;
    }

    /**
     * Sets retcode.
     *
     * @param retcode the retcode
     */
    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    /**
     * Gets retmsg.
     *
     * @return the retmsg
     */
    public Object getRetmsg() {
        return retmsg;
    }

    /**
     * Sets retmsg.
     *
     * @param retmsg the retmsg
     */
    public void setRetmsg(Object retmsg) {
        this.retmsg = retmsg;
    }
}
