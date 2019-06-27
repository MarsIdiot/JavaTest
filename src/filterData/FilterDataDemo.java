package filterData;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Date: 2019/4/29 12:09
 */
public class FilterDataDemo {

    private  String param="[{\"formId\":142,\"formName\":\"甲方名称\",\"font\":\"SimSun\",\"fontSize\":12.0,\"fontColor\":\"BLACK\",\"fontStyle\":\"Normal\",\"textAlign\":\"Left\",\"formStyle\":1,\"validateStyle\":0,\"posX\":120,\"posY\":693,\"width\":234,\"height\":19,\"pageNo\":1},{\"formId\":143,\"formName\":\"乙方名称\",\"font\":\"SimSun\",\"fontSize\":12.0,\"fontColor\":\"BLACK\",\"fontStyle\":\"Normal\",\"textAlign\":\"Left\",\"formStyle\":1,\"validateStyle\":0,\"posX\":114,\"posY\":674,\"width\":62,\"height\":16,\"pageNo\":1},{\"formId\":144,\"formName\":\"乙方身份证\",\"font\":\"SimSun\",\"fontSize\":12.0,\"fontColor\":\"BLACK\",\"fontStyle\":\"Normal\",\"textAlign\":\"Left\",\"formStyle\":1,\"validateStyle\":0,\"posX\":227,\"posY\":674,\"width\":208,\"height\":17,\"pageNo\":1},{\"formId\":145,\"formName\":\"乙方身份证2\",\"font\":\"SimSun\",\"fontSize\":12.0,\"fontColor\":\"BLACK\",\"fontStyle\":\"Normal\",\"textAlign\":\"Left\",\"formStyle\":1,\"validateStyle\":0,\"posX\":406,\"posY\":107,\"width\":170,\"height\":17,\"pageNo\":1},{\"formId\":146,\"formName\":\"年\",\"font\":\"SimSun\",\"fontSize\":12.0,\"fontColor\":\"BLACK\",\"fontStyle\":\"Normal\",\"textAlign\":\"Left\",\"formStyle\":1,\"validateStyle\":0,\"posX\":87,\"posY\":89,\"width\":50,\"height\":17,\"pageNo\":1},{\"formId\":147,\"formName\":\"月\",\"font\":\"SimSun\",\"fontSize\":12.0,\"fontColor\":\"BLACK\",\"fontStyle\":\"Normal\",\"textAlign\":\"Left\",\"formStyle\":1,\"validateStyle\":0,\"posX\":142,\"posY\":87,\"width\":27,\"height\":19,\"pageNo\":1},{\"formId\":148,\"formName\":\"日\",\"font\":\"SimSun\",\"fontSize\":12.0,\"fontColor\":\"BLACK\",\"fontStyle\":\"Normal\",\"textAlign\":\"Left\",\"formStyle\":1,\"validateStyle\":0,\"posX\":176,\"posY\":86,\"width\":28,\"height\":20,\"pageNo\":1},{\"formId\":149,\"formName\":\"年2\",\"font\":\"SimSun\",\"fontSize\":12.0,\"fontColor\":\"BLACK\",\"fontStyle\":\"Normal\",\"textAlign\":\"Left\",\"formStyle\":1,\"validateStyle\":0,\"posX\":351,\"posY\":89,\"width\":56,\"height\":17,\"pageNo\":1},{\"formId\":150,\"formName\":\"月2\",\"font\":\"SimSun\",\"fontSize\":12.0,\"fontColor\":\"BLACK\",\"fontStyle\":\"Normal\",\"textAlign\":\"Left\",\"formStyle\":1,\"validateStyle\":0,\"posX\":411,\"posY\":86,\"width\":26,\"height\":19,\"pageNo\":1},{\"formId\":151,\"formName\":\"日2\",\"font\":\"SimSun\",\"fontSize\":12.0,\"fontColor\":\"BLACK\",\"fontStyle\":\"Normal\",\"textAlign\":\"Left\",\"formStyle\":1,\"validateStyle\":0,\"posX\":445,\"posY\":85,\"width\":24,\"height\":19,\"pageNo\":1}]";

    @Test
    public  void  demo() throws FileNotFoundException {
       /* File file = new File("src\\filterData\\data.text");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes=new byte[];
        for(){

        }
        fileInputStream.read(byte);*/
        List<Data> dataList = JSONArray.parseArray(param,Data.class);
        ArrayList<Form> result = new ArrayList<>();
        for(Data data:dataList){
            Form form = new Form();
            result.add(form);
            form.setFormId(data.getFormId().toString());
            form.setFormName(data.getFormName());
        }
         System.out.println(JSON.toJSONString(result));
    }

}
