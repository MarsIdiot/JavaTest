package IO;

import org.junit.Test;

import java.text.ParseException;
import java.util.*;

public class demo {

    @Test
    public void demo1() throws ParseException {
    //##Date日期
        /*  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");
        Date date = new Date();

        String format = simpleDateFormat.format(date);//2018-08-30 星期四 17:14:43

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd E H:m:s");

        Date date1=simpleDateFormat1.parse(format);

        System.out.println(format);
        System.out.println(date1);*/
    // ##Calendar日历
   /*     Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2017);
        calendar.set(Calendar.MONTH,7);
        calendar.set(Calendar.DAY_OF_MONTH,30);
        Date date = calendar.getTime();
        System.out.println(date);

        GregorianCalendar gregorianCalendar = new GregorianCalendar(20118, 9, 20);

        int i = gregorianCalendar.get(gregorianCalendar.YEAR);
        int max=gregorianCalendar.getActualMaximum(Calendar.YEAR);
        System.out.println(i);
        System.out.println(max);*/

        //##Collection
        List<Integer>  listInt=new ArrayList<Integer>();

        listInt.add(1);
        listInt.add(2);
        listInt.add(4);
        listInt.add(0);
        //listInt.sort(Integer::compareTo);
        /*Collections.sort(listInt);
        System.out.println(listInt);*/

       /* System.out.println(listInt.getClass().toString());

        System.out.println(listInt.toArray().toString());*/

        List<String>  listStr=new ArrayList<String>();

        listStr.add("a今天");
        listStr.add("a心情");
        listStr.add("s美美");
        listStr.add("b哒!");
        //Collections.sort(listStr);
        //String s = listStr.get(1);//心情
       // String today = listStr.set(1, "today");//心情
       // String today2 = listStr.get(1);//心情
        listStr.add(1,"真的");

       /* for(String str:listStr){
            System.out.print(str);
        }*/

       // List<String>  listStr2=new ArrayList<String>(listStr);



        //Object[] arrayStr = listStr.toArray(new String[listStr.size()]);
        //System.out.println(listStr2);
        //List<Object> list = Arrays.asList(arrayStr);
        //System.out.println(list.getClass());






        //listInt.clear();//[]
        //System.out.println(listInt.size());
       // System.out.println(listInt.contains(22));

       // listInt.addAll(listInt2);//[1, 2, 4, 2, 2, 4]
       // listInt.retainAll(listInt2);//[1]
        //System.out.println(listInt);
       // System.out.println(listInt2);

        //遍历

        /*Iterator<Integer> iterator = listInt.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if(next==1){
                iterator.remove();
            }
            System.out.println(next);
        }

        System.out.println(listInt);*/


        //###Queue
        Queue<String> queueStr = new LinkedList<>();

        queueStr.add("a今天");
        queueStr.add("a心情");
        queueStr.add("s美美");
        queueStr.add("b哒!");

        /*queueStr.offer("last01");
        ((LinkedList<String>) queueStr).offerLast("last02");
        ((LinkedList<String>) queueStr).offerFirst("first");*/

       // queueStr.poll();
        /*String peek = queueStr.peek();//a今天
        System.out.println(peek);

        System.out.println(queueStr);*/


        //####Map
        Map<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("name","浪迹天涯");
        hashMap1.put("address","武汉");
        hashMap1.put("tel","1882888");
        hashMap1.put("worker","java开发工程师");

        Map<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("name","浪迹天涯2");
        hashMap2.put("address","武汉2");
        hashMap2.put("tel2","1882888");
        hashMap2.put("worker2","java开发工程师");
        hashMap1.putAll(hashMap2);
        System.out.println(hashMap1);


    }
}
