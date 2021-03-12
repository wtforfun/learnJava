package org.learn.generics;

/**
 * @author wangtao
 * @date 2021/3/8 19:31
 */
public class Animal {

    public String name;

    public Animal(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("this is " + name);
    }

}
