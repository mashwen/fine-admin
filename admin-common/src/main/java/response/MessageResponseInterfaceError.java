package response;

import java.io.Serializable;

/**
 * Created by lgc on 2017/2/10.
 */
public class MessageResponseInterfaceError extends MessageResponseInterface implements Serializable {

    private static final long serialVersionUID = 1L;
    private String errorcode;

    /**
     * Gets errorcode.
     *
     * @return the errorcode
     */
    public String getErrorcode() {
        return errorcode;
    }

    /**
     * Sets errorcode.
     *
     * @param errorcode the errorcode
     */
    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }
}
