package org.pzone.crypto;

import org.bouncycastle.math.ec.ECPoint;
import java.math.BigInteger;
import java.util.Random;

public class TestSM2 {

    private final static Random random = new Random();

    public static void main(String[] args) {

//        SM2 sm2 = new SM2();
//        SM2KeyPair keys = sm2.generateKeyPair();
//        ECPoint pubKey = keys.getPublicKey();
//        BigInteger privKey = keys.getPrivateKey();
//        byte[] data = sm2.encrypt("Hello World", pubKey);
//        System.out.println("encrypt: " + data);
//        String origin = sm2.decrypt(data, privKey);
//        System.out.println("decrypt: " + origin);

        /**
         * 生成16位AES随机密钥
         * @return
         */
        long longValue = random.nextLong();
        System.out.println("longValue = "+longValue);
        String key = String.format("%016x", longValue).toUpperCase();
        System.out.println("key = "+key);
    }

}
