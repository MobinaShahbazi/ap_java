package controller;

import database.*;
import utils.convertor;

import java.util.ArrayList;
import java.util.HashMap;


public class controller {
    public String run(String command,String data){
        //System.out.println("controller run");
        HashMap<String,String> dataMap= convertor.stringToMap(data);
        System.out.println(command);
        switch (command){
            case "login" : return login(dataMap);
            case "signUp":return addUser(dataMap);

            case "savePost" : return savePost(dataMap);
            case "viewSavedPosts" : return viewSavedPosts(dataMap);

            case "viewGList" : return viewGList();
            case "getGroupPosts" : return getGroupPosts(dataMap);

            case "editUser": return editUser(dataMap);
            case "addGroup" : return addGroup(dataMap);
            case "favorite" : return favorite(dataMap);
            case "editGroup" : return editGroup(dataMap);
            case "likePost" : return likePost(dataMap);
            case "disLikePost" : return disLikePost(dataMap);
            case "deletePost" : return deletePost(dataMap);
            case "addComment" : return addComment(dataMap);
            case "likeComment" : return likeComment(dataMap);
            case "disLikeComment" : return disLikeComment(dataMap);
        }
        return "invalid";
    }
    private String  feed(){

        return "";
    }
    private String login(HashMap<String,String> data){
        try {
            String validate="invalid\u0000";

            ArrayList<HashMap<String,String>> array=database.getInstance().getTable("users").get();

            for (int i=0;i<array.size();i++){
                if( array.get(i).get("userName").equals(data.get("userName")) && array.get(i).get("password").equals(data.get("password")) ){
                    validate="ok\u0000";
                    return validate;
                }

                else if( array.get(i).get("userName").equals(data.get("userName")) && !array.get(i).get("password").equals(data.get("password"))){
                    validate="wrong pass\u0000";
                }

            }
            return validate;
        }catch (Exception e){return e.getMessage()+"\u0000";}
    }

    private String addUser(HashMap<String,String> data){
        try {
            boolean isRepetitive=false;
            ArrayList<HashMap<String,String>> array=database.getInstance().getTable("users").get();
            //System.out.println("arr size: "+array.size());
            for (int i=0;i<array.size();i++){
                if( array.get(i).get("userName").equals(data.get("userName")) ){
                    isRepetitive=true;
                }
            }
            if(!isRepetitive){
                database.getInstance().getTable("users").insert(data);
                database.getInstance().addTable(data.get("userName"),new table("src/data/savedPosts/"+ data.get("userName") +".txt"));
                return "new userName\u0000";
            }
            else
                return "repetitive\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }

    private String viewGList(){
        System.out.println("viewGList");
        try {
            ArrayList<HashMap<String,String>> array=database.getInstance().getTable("groups").get();
            String str=convertor.arrMapToString(array);
            //System.out.println("str: "+str);
            return str+"\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }

    private String getGroupPosts(HashMap<String,String> data){
        try {
            ArrayList<HashMap<String,String>> array=database.getInstance().getTable(data.get("name")).get();
            String str=convertor.arrMapToString(array);
            return str+"\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }

    private String savePost(HashMap<String,String> data){
        System.out.println("savePost");
        try {
            database.getInstance().getTable(data.get("currentUser")).insert(data);
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }

    private String viewSavedPosts(HashMap<String,String> data){
        try {
            ArrayList<HashMap<String,String>> array=database.getInstance().getTable(data.get("currentUser")).get();
            String str=convertor.arrMapToString(array);
            return str+"\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }

    private String editUser(HashMap<String,String> data){
        try {
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }

    private String addGroup(HashMap<String,String> data){
        try {
            database.getInstance().getTable("groups").insert(data);
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }

    private String deletePost(HashMap<String,String> data){
        try {
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }

    private String addComment(HashMap<String,String> data){
        try {
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }
    private String likeComment(HashMap<String,String> data){
        try {
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }
    private String disLikeComment(HashMap<String,String> data){
        try {
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }
    private String disLikePost(HashMap<String,String> data){
        try {
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }

    private String favorite(HashMap<String,String> data){
        try {
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }
    private String editGroup(HashMap<String,String> data){
        try {
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }
    private String likePost(HashMap<String,String> data){
        try {
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }

}
