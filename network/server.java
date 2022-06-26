package network;
import database.*;

import java.net.*;

public class server {
    public void start()throws Exception{
        try {
            database.getInstance().addTable("users",new table("src/data/users.txt"));
            database.getInstance().addTable("groups",new table("src/data/groups.txt"));
            database.getInstance().addTable("IMDb",new table("src/data/groupPosts/IMDb.txt"));
            database.getInstance().addTable("Tourism",new table("src/data/groupPosts/Tourism.txt"));
            database.getInstance().addTable("savedPosts",new table("src/data/savedPosts.txt"));
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
