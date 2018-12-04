package enums;

/**
 * @Description:用法三：向枚举中添加新方法
 * @Auther:
 *  * @Date: 2018/12/4 09:57
 */
public enum Enum_3_CodeEnum {
    USER_PARAM_NAME_NONE(1001,"用户名为空"),
    USER_PARAM_PASSWORD_NONE(1002,"密码为空")
    ;
    private  Integer code;
    private  String message;
    private Enum_3_CodeEnum(Integer code, String message) {
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Enum_3_CodeEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

}
