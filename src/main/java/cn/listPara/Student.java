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
        Person student=new Person();
        student.run();
        //静态内部类才可以
//        Chinese chinese=new Chinese();
//        chinese.run();
//        printStudents(studentList);
    }

    private static void printStudents(List<Person> studentList) {
        Chinese chinese=new Chinese();
    }

    static class Chinese implements Runnable{

        @Override
        public void run() {
            System.out.println("精武英雄崛起!");

//            Jack jack=new Jack();
//            class Jack{
//                void innerClassInMethon(){
//                    System.out.println("原来方法里面还可以有类");
//                }
//            }
        }
    }

}


class Person implements Runnable{

    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("ou ye!");
    }
}
