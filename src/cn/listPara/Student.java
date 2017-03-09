package cn.listPara;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yiban on 2016/11/24.
 */
public class Student extends Person{
    int sno;

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public static void main(String[] args) {
        List<Student> studentList=new ArrayList<Student>();
//        printStudents(studentList);
    }

    private static void printStudents(List<Person> studentList) {
    }


}


class Person {

    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
