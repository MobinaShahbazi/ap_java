package network;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;
import controller.controller;

public class clientHandler extends Thread{
    private Socket socket;
    public clientHandler(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run(){
        try {
            DataInputStream dis=new DataInputStream(socket.getInputStream());
            DataOutputStream dos=new DataOutputStream(socket.getOutputStream());

            String request=dis.readUTF();
            Scanner input=new Scanner(request);
            String command=input.nextLine();
            String data=input.nextLine();

            String response=new controller().run(command,data);

            dos.writeUTF(response);
            dos.flush();
            dos.close();
            dis.close();
            socket.close();

        }catch (Exception e){}
    }
}
