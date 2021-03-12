package org.learn.serilazable;

import java.io.*;

/**
 * 为什么要进实现Serializable接口：为了保存在内存中的各种对象的状态（也就是实例变量，不是方法），
 * 并且可以把保存的对象状态再读出来，这是java中的提供的保存对象状态的机制—序列化。
 * <p>
 * 在什么情况下需要使用到Serializable接口呢？
 * 　　1、当想把的内存中的对象状态保存到一个文件中或者数据库中时候；
 * 　　2、当想用套接字在网络上传送对象的时候；
 * 　　3、当想通过RMI传输对象的时候；
 *
 * 当对象中的部分属性，不需要序列化保存时，可使用transient关键字修饰
 *
 */
public class PeopleSeri implements Serializable {

    private transient String name;
    private int age;

    //我不提供无参构造器
    public PeopleSeri(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    public static void main(String[] args) {
        //创建一个ObjectOutputStream输出流
        PeopleSeri person = null;
        PeopleSeri brady = null;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\wt\\Desktop\\object.txt"))) {
            //将对象序列化到文件s
            person = new PeopleSeri("9龙", 23);
            oos.writeObject(person);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (//创建一个ObjectInputStream输入流
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\wt\\Desktop\\object.txt"))) {
            brady = (PeopleSeri) ois.readObject();
            System.out.println(brady);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(person.hashCode());
        System.out.println(brady.hashCode());
        System.out.println(person == brady);
    }
}
