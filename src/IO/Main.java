package IO;

import org.junit.Test;

public class Main {

    @Test
    public static void main(String[] args) {
        String s = "Hello";
        //s = s + "World";
        System.out.println(s==(s+"w"));


        StringBuffer  sb=new StringBuffer("sjswhuqh");
        sb.insert(2,"爱我");

        System.out.println(sb);



    }
}
