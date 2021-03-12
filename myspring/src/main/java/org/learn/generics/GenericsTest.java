package org.learn.generics;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 泛型，通配符的使用
 * @author wangtao
 * @date 2021/3/8 19:32
 */
public class GenericsTest {


    public static void main(String[] args) {

        List<Animal> animalList = new ArrayList<>();
        Animal a1 = new Animal("a");
        Animal a2 = new Animal("b");
        animalList.add(a1);
        animalList.add(a2);


        List<Cat> cats = new ArrayList<>();
        Cat c1 = new Cat("c1");
        cats.add(c1);

        List<String> strings = new ArrayList<>();
        strings.add("str");

        /**
         * 调用不适用通配符的方法
         */
//        useNoGenerics(animalList);
//        //集合加入了其他对象，这里将报错
//        for(Animal animal : animalList){
//            animal.show();
//        }
//
//        useNoGenerics(cats);
//        //运行时，类型转换报错
//        useNoGenerics(strings);
        /**
         * 调用使用通配符的方法
         */
//        useGenericsOne(animalList);
//        useGenericsOne(cats);
//        //运行时，类型转换错误
//        useNoGenerics(strings);

        /**
         * 调用使用通配符上限的方法
         */
//        useGenericsTwo(animalList);
//        useGenericsTwo(cats);
        //编译时报错，只有是Animal类或其子类才能传入
//        useGenericsTwo(strings);

        /**
         * 调用使用通配符下限的方法
         */
        useGenericsLow(cats);
        useGenericsLow(animalList);


    }

    /**
     * 不适用泛型，传入的集合没有限制，且方法里面可随意增加任意对象，容易出错
     *
     * @param list
     */
    public static void useNoGenerics(List list) {
        for (Object o : list) {
            Animal animal = (Animal) o;
            animal.show();
        }
        //可随意增加任意对象，对集合类型造成破坏
        list.add("str");
    }


    /**
     * 使用类型通配符，传入的类型有依然没有限制，但是在方法体里面
     * ？表示指定了一种类型，只是还不知道该类型是什么，所以jvm干脆不让加对象进去
     * 但解决不了传入的参数类型不对的问题
     *
     * @param list
     */
    public static void useGenericsOne(List<?> list) {
        for (Object o : list) {
            Animal animal = (Animal) o;
            animal.show();
        }
        //不能任意加对象，编译报错
//        list.add("str");
    }

    /**
     * 使用类型通配符上限，
     * 1,限制了传入参数的类型，
     * 2,方法里面不能对list进行增加对象操作，因为传进来的类型可能是Dog 也可能是Cat
     *
     * @param list
     */
    public static void useGenericsTwo(List<? extends Animal> list) {
        for (Animal animal : list) {
            animal.show();
        }
        //不能任意加对象，编译报错
//        list.add("str");
//        list.add(new Animal(""));
    }

    /**
     * 使用通配符下限
     * 1,限制了传入参数的类型，只能是Cat类或其父类
     * 2，从list中取数据，返回的是Object，需要进行类型转换，因为父类不能转成子类
     * 3，只能保存下限指定的类型,相当于子类转父类
     *
     * @param list
     */
    public static void useGenericsLow(List<? super Cat> list) {
        for (Object object : list) {
            Cat cat = (Cat) object;
            cat.show();
        }
        //不能任意加对象，编译报错
//        list.add("str");
//        list.add(new Animal(""));
    }
}
