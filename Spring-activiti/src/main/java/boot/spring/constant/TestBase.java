package boot.spring.constant;

import java.io.InputStream;

public class TestBase {


    public static void main(String[] args) {
        /**
         * 1. Class.getResourceAsStream(String path) ： path 不以’/'开头时默认是从此类所在的包下取资源，
         *    以’/'开头则是从ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。
         * */
      InputStream is = TestBase.class.getClass().getResourceAsStream("test.txt");
//        InputStream is = TestBase.class.getClass().getResourceAsStream("/" + "test.txt");
        /**
         * 2. Class.getClassLoader.getResourceAsStream(String path) ：默认则是从ClassPath根下获取，
         *    path不能以’/'开头，最终是由ClassLoader获取资源。
         * */
        String path = TestBase.class.getResource("/").toString();
        System.out.println("path = " + path);
//        InputStream is = this.getClass().getClassLoader().getResourceAsStream("test.txt");
        byte[] bytes = new byte[1024];
        try {
            int len = is.read(bytes);
            System.out.println(new String(bytes,0,len));
        }catch (Exception e){

        }

    }
}
