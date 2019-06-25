import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Close {
    public static void main(String[] args) {

        try {
            Socket client = new Socket("192.168.1.3", 3001);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((client.getInputStream())));

            while (true) {
                String str = bufferedReader.readLine();
                System.out.println(str);
                if(str == "0"){
                    System.out.println("close");
                    //close();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void close() {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("C:\\WINDOWS\\system32\\cmd.exe");
            OutputStream os = process.getOutputStream();
            os.write("shutdown -s -f -t 90 \n\r".getBytes());
            os.close();
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
