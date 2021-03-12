package org.pzone.cer;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;

public class GenSM2Certificate {
 
	// 产生 SM2 国密证书
	public static void genCertificate(){
		Security.addProvider(new BouncyCastleProvider());
		try {
			KeyPair kp = SM2KeyStore.genSM2KeyPair();							// 生成SM2公私钥对
			BCECPrivateKey bcecPrivateKey = (BCECPrivateKey) kp.getPrivate();   // 使用ECPrivateKey\PrivateKey都可以										
			BCECPublicKey bcecPublicKey = (BCECPublicKey) kp.getPublic();       // 使用ECPublicKey\PublicKey都可以
																	
			X500Principal principal = new X500Principal("CN=jy,O=info"); 	// subjectDN
			// 填充 证书信息
			X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
			certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
			certGen.setIssuerDN(principal);
			certGen.setNotBefore(new Date());
			certGen.setNotAfter(new Date(new Date().getTime() + 1000 * 3600 * 24 * 10));
			certGen.setSubjectDN(principal);
			certGen.setSignatureAlgorithm("1.2.156.10197.1.501");
			certGen.setPublicKey(bcecPublicKey);
			// 构造 SM2 证书
			X509Certificate rootCert = certGen.generateX509Certificate(bcecPrivateKey, "BC");
			// 保存 证书
			String filepath = "C:\\Users\\wt\\Desktop\\sm2_cer_from_jy_01.cer";
			File file = new File(filepath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(rootCert.getEncoded());
			fos.close();
			System.out.println("succ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("fail");
			return;
		}
	}
	
	public static void main(String[] args) {
		
		genCertificate();
	}
}