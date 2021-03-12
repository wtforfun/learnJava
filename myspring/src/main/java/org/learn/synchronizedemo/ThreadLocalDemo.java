package org.learn.synchronizedemo;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ThreadLocalDemo {

//    private static final AtomicInteger nextId = new AtomicInteger(0);
//
//    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
//        @Override
//        protected Integer initialValue() {
//            return nextId.getAndIncrement();
//        }
//    };
//
//    public static void main(String[] args) {
//
//
//        System.out.println(threadId.get());
//        threadId.set(10);
//        System.out.println(threadId.get());
//        System.out.println(threadId.get());
//
//
//    }

        private static final ThreadLocal<Integer> t1 = new ThreadLocal<Integer>();

        private static final ThreadLocal<Integer> t2 = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {
                return 21;
            }
        };

        private static final ThreadLocal<Integer> t3 = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {
                return 31;
            }
        };

    public static void main(String[] args) throws Exception{
        System.out.println(t1.get());
        t1.set(11);
        System.out.println(t1.get());
        System.out.println(t2.get());
        System.out.println(t3.get());
        Object obj = new Object();
        Thread thread = Thread.currentThread();
        Class clazz = Thread.class;
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            String name = field.getName();
            field.setAccessible(true);
            Object resultValue = field.get(thread);
            if("threadLocals".equals(name)){
                obj = resultValue;
            }
            System.out.println(name + " : "+resultValue);

        }

        System.out.println(obj);

    }


}
