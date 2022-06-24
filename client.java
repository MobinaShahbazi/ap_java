import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("127.0.0.1",8000);
        DataInputStream dis=new DataInputStream(socket.getInputStream());
        DataOutputStream dos=new DataOutputStream(socket.getOutputStream());

        dos.writeUTF("send\nmassage:hello every one,,me:mobina");//payam az front
        dos.flush();
        System.out.println(dis.readUTF());
        dos.close();
        dis.close();
        socket.close();
    }
}
