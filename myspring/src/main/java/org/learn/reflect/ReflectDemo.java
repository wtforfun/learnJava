package org.learn.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射
 */
public class ReflectDemo {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReflectDemo (){}

    public ReflectDemo(String name){
        this.name = name;
    }

    public void hi(){
        System.out.println("hi");
    }

    public static void main(String[] args)throws Exception {

        ReflectDemo reflectDemo = new ReflectDemo("Wt");
        /**
         *         获取类对象的三种方法
         */
        //1，通过对象获取
        Class clazz1 = reflectDemo.getClass();

        //2，通过类获取
        Class clazz2 = ReflectDemo.class;

        //3，通过类路径过去（比较常用，比较灵活，路径可以存放在配置文件或数据库中）
        Class clazz3 = Class.forName("org.learn.reflect.ReflectDemo");

        //通过类的类对象，判断对象是否是该类的实例
        boolean f = ReflectDemo.class.isInstance(reflectDemo);
        System.out.println(f);

        /**
         *        通过反射 获取所有的属性
         */
        Field[] fields = clazz1.getDeclaredFields();
        for(Field field : fields){
            System.out.println(field.getName());
            System.out.println(field.get(reflectDemo));
        }
        /**
         *      通过反射   获取所有的方法
         */
        Method[] methods = clazz1.getDeclaredMethods();
        /**
         *      通过反射  获取构造方法
         */
        Constructor constructor = clazz1.getDeclaredConstructor(String.class);

        /**
         * 通过类对象，创建对象
         */
        Object object = clazz1.newInstance();

    }
}
