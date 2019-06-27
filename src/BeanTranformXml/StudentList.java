package BeanTranformXml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Description:
 * @Date: 2019/5/21 18:21
 */
@XmlRootElement(name = "list")
public class StudentList {
    private List<Student> studentList;

    @XmlElementWrapper
    @XmlElement(name = "student")
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
