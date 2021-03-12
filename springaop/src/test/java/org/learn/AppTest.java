package org.learn;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testUnicode() throws Exception {
        String s1 = "学习";
        System.out.println(s1.toLowerCase());
        byte[] s1Bytes = s1.getBytes();
        for(byte b : s1Bytes){
            System.out.print(b);
        }
        System.out.println();
        String s2 = "学";
        byte[] s2Bytes = s2.getBytes();
        for(byte b : s2Bytes){
            System.out.print(b);
        }

        System.out.println();
        double money = 1.0 - 0.9;
        System.out.println(money);

        final String a= "1";

    }
}
