package network;
import database.*;

import java.net.*;

public class server {
    public void start()throws Exception{
        try {
            database.getInstance().addTable("users",new table("src/data/users.txt"));
            ServerSocket ss=new ServerSocket(3000);
            while (true){
                Socket socket=ss.accept();
                new clientHandler(socket).start();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
