import boot.spring.util.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.models.auth.In;
import org.junit.Test;

import java.io.*;
import java.util.Date;

public class Test3 {

    @Test
    public void testTime(){
        String time1 = "2019-08-08 09:08:49";
        String time2 = "2019-08-08 09:08:50";

        boolean f = DateTimeUtil.gteTime(time1,time2);
        System.out.println(f);
    }

    @Test
    public void testTime2() throws Exception{

        Date date1 = new Date();
        Thread.sleep(10);
        Date date2 = new Date();
        boolean f = DateTimeUtil.gteTime(date1,date2);
        System.out.println(f);
    }

    /**
     * 使用文件输出流 生成文件
     * */
    @Test
    public void testNewFile() throws Exception{
        File file = new File("C:\\Users\\wt\\Desktop"+File.separator+"test.txt");
        OutputStream outputStream = new FileOutputStream(file);
        String str = "hello world";
        byte[] bytes = str.getBytes();
        outputStream.write(bytes);
        outputStream.close();

    }

    @Test
    public void testNewFile2() throws Exception{
        File file = new File("C:\\Users\\wt\\Desktop"+File.separator+"test.txt");
        OutputStream outputStream = new FileOutputStream(file,true);
        String str = "hello world";
        byte[] bytes = str.getBytes();
        for(int i = 0;i<bytes.length;i++){
            outputStream.write(bytes[i]);
        }
        outputStream.close();

    }

    /**
     * 从文件中读取数据
     * */
    @Test
    public void testNewFile3() throws Exception{
        File file = new File("C:\\Users\\wt\\Desktop\\test.txt");
        InputStream inputStream = new FileInputStream(file);
        byte[] b=new byte[(int) file.length()];
        int len=inputStream.read(b);
        System.out.println(new String(b,0,len));
        inputStream.close();
    }

    /**
     * 从文件中读取数据
     * */
    @Test
    public void testNewFile4() throws Exception{
        File file = new File("C:\\Users\\wt\\Desktop\\test.txt");
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        for(int i = 0;i<bytes.length;i++){
            bytes[i] = (byte) inputStream.read();
        }
        inputStream.close();
        System.out.println(new String(bytes));
    }


    /**
     * 字符流写入操作
     * */
    @Test
    public void testNewFile5() throws Exception{
        File file = new File("C:\\Users\\wt\\Desktop"+File.separator+"test.txt");
        Writer writer = new FileWriter(file,true);
        String str = "test fileWriter";
        writer.write(str);
        writer.close();
    }

    @Test
    public void testFileOperation1() throws Exception{
//        File file = new File("C:\\Users\\wt\\Desktop"+File.separator+"test.txt");
//        Reader reader = new FileReader(file);
//        char[] chars = new char[1024];
//        int len = reader.read(chars);
//        reader.close();
//        System.out.println(new String(chars,0,len));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\wt\\Desktop\\test.txt")));
        String lineStr = null;
        while((lineStr = bufferedReader.readLine()) != null){
            System.out.println(lineStr);
        }
        bufferedReader.close();
    }

    @Test
    public void testGetResources() throws Exception{
        /**
         * 1. Class.getResourceAsStream(String path) ： path 不以’/'开头时默认是从此类所在的包下取资源，
         *    以’/'开头则是从ClassPath根下获取。其只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源。
         * */
//      InputStream is = this.getClass().getResourceAsStream("test.txt");
        InputStream is = this.getClass().getResourceAsStream("/" + "test.txt");
        /**
         * 2. Class.getClassLoader.getResourceAsStream(String path) ：默认则是从ClassPath根下获取，
         *    path不能以’/'开头，最终是由ClassLoader获取资源。
         * */
//        InputStream is = this.getClass().getClassLoader().getResourceAsStream("test.txt");
        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
        System.out.println(new String(bytes,0,len));
    }

    @Test
    public void testGuid(){

    }




}
