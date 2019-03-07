package Exception;

/**
 * @Description:
 * @Date: 2019/3/1 17:02
 */
public class MyBusinessException  extends RuntimeException {
    private String errCode;
    private String message;

    public MyBusinessException(String errCode, String message) {
        this.errCode = errCode;
        this.message = message;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
