import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class client {
    public static void main(String[] args) {
        System.out.println("asd");
        try {
            Socket client = new Socket("127.0.0.1", 3001);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((client.getInputStream())));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            bufferedWriter.write("data");
            System.out.println("test");
            String str = bufferedReader.readLine();
            System.out.println(str);
            str = bufferedReader.readLine();
            System.out.println(str);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
