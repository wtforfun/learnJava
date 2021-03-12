package org.pzone.cer;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

public class SM2KeyStore {
 
	// 产生 SM2 的密钥对
	public static KeyPair genSM2KeyPair() throws Exception {
		// 获取SM2椭圆曲线的参数
		final ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");//
		// 获取一个椭圆曲线类型的密钥对生成器
		final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
		// 使用SM2参数初始化生成器
		kpg.initialize(sm2Spec);
		// 使用SM2的算法区域初始化密钥生成器
		kpg.initialize(sm2Spec, new SecureRandom());
		// 获取密钥对
		KeyPair sm2keyPair = kpg.generateKeyPair();
		return sm2keyPair;
	}
	
	// 测试
	public static void main(String[] args) throws Exception {
		
		KeyPair keyPair = genSM2KeyPair();
		keyPair.getPublic();
		keyPair.getPrivate();
	}

}