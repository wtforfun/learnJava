package org.learn;

import org.junit.Test;

import java.security.SecureRandom;

/**
 * @author wangtao
 * @date 2021/3/6 12:28
 */
public class StringTest {

    @Test
    public void test1() {
        String str = "abc";
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        System.out.println(sb.toString());
        StringBuilder sb2 = new StringBuilder(str);
        System.out.println(sb2.reverse());

    }
}
