/*  shaw 2018-12-17
* �����ŷ�ӽ���
*   ֤�飨1�������֤�飬2������ǩ��֤�飩
*  ������ҳ��https://user.qzone.qq.com/1939484341/infocenter
*/
package psic;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import server.ClientConf;
import server.GetEncPvk;
import server.JPkcs7;
import server.Util;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Security;;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

public class SHAWApp {

    private CertificateFactory cf = null;
    private static final Map algMap = new HashMap();

    public SHAWApp() {
        try {
            Security.addProvider(new BouncyCastleProvider());
            this.cf = CertificateFactory.getInstance("X.509", "BC");
            algMap.put(32772, "sha1withrsa");
            algMap.put(32780, "sha256withrsa");
            algMap.put(32771, "md5withrsa");
            System.out.println("SHAWApp��ʼ���ɹ�");
        } catch (Exception var) {
            System.out.println("SHAWApp��ʼ���쳣��" + var.getMessage());
        }

    }

    //��ȡ֤��
    public String ShawGetCert(String keyLabel, int certType) {
        String result = "";
        Object[] objs = new Object[]{certType};
        if (!Util.paramCheck(objs)) {
            System.out.println("��Ҫ����β���Ϊ�ջ�null");
            return result;
        } else {
            String certPath = "";
            String certTypePath = "";
            try {
                ClientConf conf = new ClientConf();
                String configPath = conf.CONFIG_PATH;
                if (keyLabel == null || "".equals(keyLabel)) {
                    keyLabel = conf.getParamValue("KeyLabel");
                }
                certPath = configPath.substring(0, configPath.indexOf("Client.conf")) + keyLabel + System.getProperty("file.separator");
            } catch (Exception var) {
                System.out.println("��ȡ�����ļ�ʧ�ܣ�" + var.getMessage());
                return result;
            }
            try {
                String certTypeString = "";
                if (certType == 1) {
                    certTypeString = "-CertEx.cer";
                } else {
                    if (certType != 2) {
                        throw new Exception("��������");
                    }
                    certTypeString = "-CertSig.cer";
                }
                certTypePath = certPath + keyLabel + certTypeString;
                FileInputStream fileInputStream = new FileInputStream(certTypePath);
                byte[] b = new byte[fileInputStream.available()];
                fileInputStream.read(b);
                fileInputStream.close();
                result = (new BASE64Encoder()).encode(b);
            } catch (Exception var) {
                System.out.println("��ȡ֤��ʧ�ܣ�" + var.getMessage());
            }
            return result;
        }
    }

    //�����ŷ����
    public String ShawSealEnvelope(String i_encCert, int i_symmAlgo, byte[] i_inData) {
        String base64Res = "";
        Object[] objs = new Object[]{i_encCert, i_symmAlgo, i_inData};
        if (!Util.paramCheck(objs)) {
            System.out.println("��Ҫ����β���Ϊ�ջ�null");
            return base64Res;
        } else {
            try {
                byte[] certBytes = org.bouncycastle.util.encoders.Base64.decode(i_encCert);
                InputStream inputStream = new ByteArrayInputStream(certBytes);
                X509Certificate cert = (X509Certificate)this.cf.generateCertificate(inputStream);
                X509Certificate[] certs = new X509Certificate[]{cert};
                JPkcs7 jPkcs7 = new JPkcs7();
                Cipher sessionKeyCipher = Cipher.getInstance("RSA/None/PKCS1Padding");
                Cipher sealOrignalChipher = Cipher.getInstance("DESede");
                byte[] resBytes = jPkcs7.makePKCS7ENC(certs, i_inData, i_symmAlgo, sessionKeyCipher, sealOrignalChipher);
                base64Res = new String(org.bouncycastle.util.encoders.Base64.encode(resBytes));
                return base64Res;
            } catch (Exception var) {
                System.out.println("�ŷ����ʧ�ܣ�" + var.getMessage());
                return base64Res;
            }
        }
    }

    //�����ŷ����
    public byte[] ShawOpenEnvelope(String keyLabel, String keyPasswd, String i_inData) {
        byte[] orginal = (byte[])null;
        Object[] objs = new Object[]{i_inData};
        if (!Util.paramCheck(objs)) {
            System.out.println("��Ҫ����β���Ϊ�ջ�null");
            return orginal;
        } else {
            try {
                String certAndKeyPath = "";
                String keyPath = "";

                try {
                    ClientConf conf = new ClientConf();
                    String configPath = conf.CONFIG_PATH;
                    if (keyLabel == null || "".equals(keyLabel)) {
                        keyLabel = conf.getParamValue("KeyLabel");
                    }

                    certAndKeyPath = configPath.substring(0, configPath.indexOf("Client.conf")) + keyLabel + System.getProperty("file.separator");
                    keyPath = certAndKeyPath + "pvkExh";
                } catch (Exception var) {
                    System.out.println("��ȡ�����ļ�ʧ�ܣ�" + var.getMessage());
                    return orginal;
                }
                FileInputStream fileInputStream = new FileInputStream(keyPath);
                byte[] keyFile = new byte[fileInputStream.available()];
                fileInputStream.read(keyFile);
                fileInputStream.close();
                GetEncPvk getEncPvk = new GetEncPvk();
                if (!"".equals(keyPasswd) && keyPasswd != null) {
                    keyFile = getEncPvk.getKey(keyPasswd, keyFile);
                }
                JPkcs7 jPkcs7 = new JPkcs7();
                Cipher openlOrignalChipher = Cipher.getInstance("DESede");
                orginal = jPkcs7.openPKCS7ENC(keyFile, i_inData, openlOrignalChipher);
                return orginal;
            } catch (Exception var) {
                System.out.println("�ŷ����ʧ�ܣ�" + var.getMessage());
                return orginal;
            }
        }
    }

    //����
    public static void main(String[] args) throws Exception {

        String keyLabel = "Test1024";
        SHAWApp shawApp = new SHAWApp();

        String certS = shawApp.ShawGetCert(keyLabel, 2);
        System.out.println("����֤����Ϣ��" + certS);

        String encryption = shawApp.ShawSealEnvelope(certS, 26625, "123".getBytes());
        System.out.println("�����ŷ���ܽ��:" + encryption);

        byte[] decryption = shawApp.ShawOpenEnvelope(keyLabel, "", encryption);
        String result12 = new String(decryption);
        System.out.println("�����ŷ���ܽ��:" + result12);

    }
}
