import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class client {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("127.0.0.1",8000);
        DataInputStream dis=new DataInputStream(socket.getInputStream());
        DataOutputStream dos=new DataOutputStream(socket.getOutputStream());

        dos.writeBytes("send\nmassage:hello every one,,me:mobina\u0000");//payam az front
        dos.flush();
        StringBuilder str=new StringBuilder();
        int i= dis.read();
        while (i != 0){
            str.append((char) i);
            i= dis.read();
        }
        System.out.println(str);
        dos.close();
        dis.close();
        socket.close();
    }
}
