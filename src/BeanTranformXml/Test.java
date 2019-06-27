package BeanTranformXml;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Date: 2019/5/21 18:05
 */
public class Test {

    @org.junit.Test
    public void testStudent() throws JAXBException {
        Student student = new Student();
        student.setName("普罗旺斯的秘密");
        student.setSex("male");
        student.setClassName("1981");
        List<String> hobby = new ArrayList<>();
        hobby.add("葡萄园");
        hobby.add("特色炖汤");
        hobby.add("牛奶芝士");
        hobby.add("麻辣龙虾");
        student.setHobby(hobby);

        String resultXMl = BeanToXmlUtil.beanToXml(student, Student.class);

    }

    @org.junit.Test
    public void testStudentList() throws JAXBException {

        List<Student> list=new ArrayList<>();
        Student student = new Student();
        student.setName("普罗旺斯的秘密");
        student.setSex("male");
        student.setClassName("1981");
        List<String> hobby = new ArrayList<>();
        hobby.add("葡萄园");
        hobby.add("特色炖汤");
        hobby.add("牛奶芝士");
        hobby.add("麻辣龙虾");
        student.setHobby(hobby);
        list.add(student);

        Student student2 = new Student();
        student2.setName("普罗旺斯的秘密2");
        student2.setSex("male2");
        student2.setClassName("19812");
        List<String> hobby2 = new ArrayList<>();
        hobby2.add("葡萄园2");
        hobby2.add("特色炖汤2");
        hobby2.add("牛奶芝士2");
        hobby2.add("麻辣龙虾2");
        student2.setHobby(hobby2);
        list.add(student2);

        StudentList students = new StudentList();
        students.setStudentList(list);
        String resultXMl = BeanToXmlUtil.beanToXml(students, StudentList.class);

    }
}
