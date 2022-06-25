package network;
import java.net.*;

public class server {
    public void start()throws Exception{
        ServerSocket ss=new ServerSocket(3000);
        while (true){
            Socket socket=ss.accept();
            new clientHandler(socket).start();
        }
    }
}
