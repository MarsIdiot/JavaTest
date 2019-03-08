package collection.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:现有List<Hotel>，根据hotelId来分别获取对应的number属性。其结果为Map<String,List<String>>
 * @Date: 2019/3/8 16:45
 */
public class demo1 {

    private List<Hotel> hotelList;

    @Test
    public void getCountByHotelId(){

        //0、数据获取
        this.getlist();

        //1、循环List<Hotel>得到所有的hotelIds
        List<String>  hotelIds=new  ArrayList<>();//所有得hotelId
        for(Hotel hotel:hotelList){
            if(!hotelIds.contains(hotel.getHotelId())){//判断再集合中是否包含当前循环的Id,如果没有，则加入。(过滤掉重复的Id)
                hotelIds.add(hotel.getHotelId());
            }
        }

        //2、外层循环ids  内层循环List<Hotel>，如果有与id相同得，则加入到map中
        Map<String,List<String>> map=new HashMap<>();//结果
        for(String hotelId:hotelIds){
            List<String> numberList=new ArrayList<>();//存储当前hotelId对应的number
            map.put(hotelId,numberList);
            for(Hotel hotel:hotelList){
                if(hotelId.equals(hotel.getHotelId())){//如果有与当前id相同对应的number加入到numberList中
                    numberList.add(hotel.getNumber());//加入
                }
            }
        }

        System.out.println("xx");


    }

    @Test
    public  void getlist(){
        List<Hotel> hotelList=new ArrayList<>();

        Hotel hotel1=new Hotel();
        hotel1.setHotelId("1");
        hotel1.setNumber("1-100");
        hotelList.add(hotel1);

        Hotel hotel2=new Hotel();
        hotel2.setHotelId("1");
        hotel2.setNumber("1-200");
        hotelList.add(hotel2);

        Hotel hotel3=new Hotel();
        hotel3.setHotelId("2");
        hotel3.setNumber("2-100");
        hotelList.add(hotel3);

        Hotel hotel4=new Hotel();
        hotel4.setHotelId("2");
        hotel4.setNumber("2-200");
        hotelList.add(hotel4);

        Hotel hotel5=new Hotel();
        hotel5.setHotelId("3");
        hotel5.setNumber("3-100");
        hotelList.add(hotel5);

        Hotel hotel6=new Hotel();
        hotel6.setHotelId("3");
        hotel6.setNumber("3-300");
        hotelList.add(hotel6);

        Hotel hotel7=new Hotel();
        hotel7.setHotelId("3");
        hotel7.setNumber("3-200");
        hotelList.add(hotel7);

        this.hotelList=hotelList;
    }



}
