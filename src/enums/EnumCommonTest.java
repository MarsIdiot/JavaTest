package enums;

import org.junit.Test;

import static enums.Enum_3_CodeEnum.USER_PARAM_NAME_NONE;
import static enums.Enum_3_CodeEnum.USER_PARAM_PASSWORD_NONE;

/**
 * @Description:
 * @Auther:
 * @Date: 2018/12/4 10:06
 */
public class EnumCommonTest {
    @Test
    public  void finalEnumTest(){
        Enum_1_FinalEnum blueEnum = Enum_1_FinalEnum.BLUE;
        System.out.println(blueEnum.toString());
        System.out.println(blueEnum.ordinal());//获取序号
    }

    @Test
    public  void codeEnumTest(){

        /*
        获取所有的枚举对象
        输出：
        Enum_3_CodeEnum{code=1001, message='用户名为空'}
        Enum_3_CodeEnum{code=1002, message='密码为空'}
         */
        Enum_3_CodeEnum[] codeEnums = Enum_3_CodeEnum.values();
        for (Enum_3_CodeEnum codeEnums1: codeEnums){
            System.out.println(codeEnums1);
        }

        /*
        通过枚举名称获取对应的CodeEnum
         */
        Enum_3_CodeEnum user_param_name_none = Enum_3_CodeEnum.valueOf("USER_PARAM_NAME_NONE");
        System.out.println(user_param_name_none.getCode()+":"+user_param_name_none.getMessage());

        /*
        直接通过类名获取
         */
        String string = USER_PARAM_NAME_NONE.toString();
        System.out.println(string);

    }

    /*
    switch case 比较枚举对象
     */
    @Test
    public void switchCaseTest(){
        //根据code获取枚举名
        Enum_3_CodeEnum codeName_1001 = null;
        Enum_3_CodeEnum[] codeEnums = Enum_3_CodeEnum.values();
        for(Enum_3_CodeEnum codeEnum:codeEnums){
            if(codeEnum.getCode()==1001){
                codeName_1001=codeEnum;
            }
        }

        //使用switch case
        switch (codeName_1001){
            case USER_PARAM_NAME_NONE:
                System.out.println(USER_PARAM_NAME_NONE.toString());
                break;
            case USER_PARAM_PASSWORD_NONE:
                System.out.println(USER_PARAM_PASSWORD_NONE.toString());
        }

    }
}
