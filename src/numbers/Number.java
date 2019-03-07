package numbers;

import org.junit.Test;

/**
 * @Description:
 * @Date: 2019/2/26 15:36
 */
public class Number {
    @Test
    public void printFormatTest(){
        float floatVar=1.3f;
        int intVar=1;
        String stringVar="jack ma";
        System.out.format(""+"浮点变量的值为"+
     "%f，而"+"整数变量的值为%d,"+
     "且字符串为%s",floatVar, intVar,stringVar);
    }


    @Test
    public void mathTest(){
        double v = Math.random() * 10;
        System.out.println(v);
    }
    @Test
    public void test(){
        //将整数65转换为16进制的字符串41
        String s = Integer.toHexString(65);
        System.out.println(s);//41

        //将数字字符串转换为等效字符串int
        int i = Integer.parseInt("230");
        System.out.println(i);//230

        //Double方法来检测浮点数是否具有特殊值Not a Number（NaN
        boolean naN = Double.isNaN(1);
        System.out.println(naN);//false

        //将基数为5的字符串转换为等效字符串int
        Integer integer = Integer.valueOf("230", 5);
        System.out.println(integer);//65

        //equals
        boolean equals = Integer.valueOf(1).equals(Long.valueOf(1));
        System.out.println(equals);//false

    }




}











