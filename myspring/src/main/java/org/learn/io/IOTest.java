package org.learn.io;

import java.io.*;

/**
 * @author wangtao
 * @date 2021/3/6 13:00
 */
public class IOTest {

    public static void main(String[] args) throws Exception{



        String filePath = "C:\\Users\\wt\\Desktop\\test.txt";
        File file= new File(filePath);
        //io分为字符流(Reader/Writer接口)和字节流（inputStream、outputStream接口）
        Reader reader = new FileReader(file);
        InputStream is = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        FileReader fr = new FileReader(file);
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String line;
        while((line = bf.readLine())!=null){
            System.out.println(line);
        }

    }
}
