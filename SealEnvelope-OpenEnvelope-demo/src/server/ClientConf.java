package server;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class ClientConf {
    public String CONFIG_PATH = "";

    public ClientConf() {
        String Os_Name = System.getProperty("os.name").toLowerCase();
        String File_Sep = System.getProperty("file.separator");
        if (Os_Name.indexOf("win") != -1) {
            this.CONFIG_PATH = "C:" + File_Sep + "Config" + File_Sep + "Client.conf";
        }

        if (Os_Name.indexOf("linux") != -1 || Os_Name.indexOf("unix") != -1 || Os_Name.indexOf("ux") != -1 || Os_Name.indexOf("sun") != -1 || Os_Name.indexOf("sunos") != -1 || Os_Name.indexOf("aix") != -1) {
            this.CONFIG_PATH = File_Sep + "usr" + File_Sep + "local" + File_Sep + "Config" + File_Sep + "Client.conf";
        }

    }

    public String getParamValue(String paramName) {
        byte[] buf = new byte[1024];

        try {
            ByteArrayOutputStream b_out = new ByteArrayOutputStream();
            FileInputStream fin = new FileInputStream(this.CONFIG_PATH);

            int j;
            while((j = fin.read(buf, 0, buf.length)) != -1) {
                b_out.write(buf, 0, j);
                b_out.flush();
            }

            String fileBuffer = b_out.toString().trim();
            b_out.close();
            fin.close();
            int index1 = fileBuffer.indexOf("<" + paramName + ">");
            int index2 = fileBuffer.indexOf("</" + paramName + ">");
            String paramValue = fileBuffer.substring(index1 + (new String("<" + paramName + ">")).length(), index2);
            return paramValue;
        } catch (Exception var10) {
            return null;
        }
    }
}
