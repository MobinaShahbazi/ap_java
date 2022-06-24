package controller;

import database.database;
import utils.convertor;
import java.util.HashMap;

public class controller {
    public String run(String command,String data){
        System.out.println("controller run");
        HashMap<String,String> dataMap= convertor.stringToMap(data);
        System.out.println(command);
        switch (command){
            case "send" : return send(dataMap);
            case "savePost":return savePost();

        }
        return "invalid";
    }

    private String send(HashMap<String,String> data){
        try {
            database.getInstance().getTable("massages").insert(data);//change massages to addusar
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}

    }

    private String savePost(){
        return "";
    }
}
