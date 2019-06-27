package BeanTranformXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * @Description:
 * @Date: 2019/5/21 18:14
 */
public class BeanToXmlUtil {

    /**
     * 18      * java对象转换为xml文件
     * 19      * @param xmlPath  xml文件路径
     * 20      * @param load    java对象.Class
     * 21      * @return    xml文件的String
     * 22      * @throws JAXBException
     * 23
     */
    public static String beanToXml(Object obj, Class<?> load) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(load);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        return writer.toString();
    }
}
