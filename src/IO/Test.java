package IO;

public class Test {

    @org.junit.Test
    public  void Test1(){

        String str = "12309abcdABCD";
        /*//1.将字符串全部存进数组
        char[] chars = str.toCharArray();*/

       // System.out.println(str.codePointAt(0));
        //2.循环数组判断--大写字母/小写字母/数字
        int UpperCaseCount=0;
        int lowerCaseCount=0;
        int numberCount=0;
        for(int i=0;i<str.length();i++){
            //大写字母
            if(str.codePointAt(i)<=90&str.codePointAt(i)>=65){
                UpperCaseCount++;
            }
            if(str.codePointAt(i)>=97&str.codePointAt(i)<=122){//小写字母
                lowerCaseCount++;
            }
            if(str.codePointAt(i)>=48&str.codePointAt(i)<=57){//数字
                numberCount++;
            }
        }
        System.out.println("大写字母："+UpperCaseCount);
        System.out.println("小写字母："+lowerCaseCount);
        System.out.println("数字："+numberCount);
    }
}
