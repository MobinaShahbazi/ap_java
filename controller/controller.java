package controller;

import database.database;
import utils.convertor;
import java.util.HashMap;

public class controller {
    public String run(String command,String data){
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
            database.getInstance().getTable("massages").insert(data);
            return "massage successfully saved";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    private String savePost(){
        return "";
    }
}
