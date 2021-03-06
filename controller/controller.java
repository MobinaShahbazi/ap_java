package controller;

import database.*;
import utils.convertor;
import java.util.ArrayList;
import java.util.HashMap;

public class controller {
    public String run(String command,String data){
        HashMap<String,String> dataMap= convertor.stringToMap(data);
        System.out.println(command);
        switch (command){
            case "login" : return login(dataMap);
            case "signUp":return addUser(dataMap);
            case "savePost" : return savePost(dataMap);
            case "viewSavedPosts" : return viewSavedPosts(dataMap);
            case "viewGList" : return viewGList();
            case "getGroupPosts" : return getGroupPosts(dataMap);
            case "viewFeed" : return feed();
            case "editUser": return editUser(dataMap);
            case "isRepetitive": return isRepetitive(dataMap);
            case "unSavePost": return unSavePost(dataMap);
            case "addGroup" : return addGroup(dataMap);
            case "addPost" : return addPost(dataMap);
            case "removePost" : return removePost(dataMap);
            case "changeFavorite" : return favorite(dataMap);
            case "likeDislike" : return likePost(dataMap);
            case "deletePost" : return deletePost(dataMap);
            case "addComment" : return addComment(dataMap);
            case "likeDislikeComment" : return likeComment(dataMap);
            case "getComments" : return getComments(dataMap);
        }
        return "invalid";
    }
    private String  feed(){
        try {
            ArrayList<HashMap<String,String>> array=database.getInstance().getTable("posts").get();
            return convertor.arrMapToString(array);
        }catch (Exception e){return e.getMessage();}

    }
    private String  addPost(HashMap<String,String> data){
        try {
            database.getInstance().addTable(data.get("title"),new table("src/data/comments/"+data.get("title")+".txt"));
            database.getInstance().getTable("posts").insert(data);
            database.getInstance().getTable(data.get("groupName")).insert(data);
            return "massage successfully saved\u0000";
        }catch (Exception e){return e.getMessage();}
    }
    private String  getComments(HashMap<String,String> data){
        try {
            System.out.println(data.get("title"));
            ArrayList<HashMap<String,String>> array=database.getInstance().getTable(data.get("title")).get();
            System.out.println(array.size());
            return convertor.arrMapToString(array)+"\u0000";
        }catch (Exception e){return e.getMessage();}

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
            if(validate.equals("ok\u0000")){
                database.getInstance().addTable(data.get("userName"),new table("src/data/savedPosts/"+ data.get("userName") +".txt"));
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

    private String isRepetitive(HashMap<String,String> data){
        try {
            System.out.println("lilo");
            boolean isRepetitive=false;
            ArrayList<HashMap<String,String>> array=database.getInstance().getTable("users").get();
            for (int i=0;i<array.size();i++){
                if( array.get(i).get("userName").equals(data.get("userName")) ){
                    isRepetitive=true;
                }
            }
            if(isRepetitive)
                return "repetitive\u0000";
            return "new userName\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }

    private String viewGList(){
        System.out.println("viewGList");
        try {
            ArrayList<HashMap<String,String>> array=database.getInstance().getTable("groups").get();
            String str=convertor.arrMapToString(array);
            System.out.println(array.size());
            System.out.println("str: "+str);
            return str+"\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }

    private String getGroupPosts(HashMap<String,String> data){
        try {
            ArrayList<HashMap<String,String>> array=database.getInstance().getTable(data.get("name")).get();
            String str=convertor.arrMapToString(array);
            System.out.println(str);
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
            System.out.println(data.get("currentUser"));
            ArrayList<HashMap<String,String>> array=database.getInstance().getTable(data.get("currentUser")).get();
            System.out.println(array.size());
            String str=convertor.arrMapToString(array);
            System.out.println(str);
            return str+"\u0000";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "\u0000";}
    }
    private String editUser(HashMap<String,String> data){
        try {
            System.out.println(":::"+data.get("preUserName"));
            ArrayList<HashMap<String,String>> users=database.getInstance().getTable("users").get();
            ArrayList<HashMap<String,String>> newUsers=new ArrayList<>();
            for(int i=0;i< users.size();i++){
                if(!users.get(i).get("userName").equals(data.get("preUserName"))){
                    newUsers.add(users.get(i));
                    //System.out.println(users.get(i).get("userName") +"   "+data.get("preUserName"));
                }
            }
            database.getInstance().getTable(data.get("preUserName")).remane("src/data/savedPosts/"+data.get("userName")+".txt");
            database.getInstance().addTable(data.get("userName"),new table("src/data/savedPosts/"+data.get("userName")+".txt"));
            data.remove("preUserName");
            newUsers.add(data);
            database.getInstance().getTable("users").clear();
            for(int i=0;i< newUsers.size();i++){
                database.getInstance().getTable("users").insert(newUsers.get(i));
            }
            return "massage successfully saved\u0000";
        }catch (Exception e){return e.getMessage()+"\u0000";}
    }
    private String addGroup(HashMap<String,String> data){
        try {
            database.getInstance().addTable(data.get("name"),new table("src/data/groupPosts/"+data.get("name")+".txt"));
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
            database.getInstance().getTable(data.get("title")).insert(data);
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }
    private String favorite(HashMap<String,String> data){
        System.out.println("fav");
        try {
            int index=indexOfGrp(data.get("name"));
            ArrayList<HashMap<String,String>> array=database.getInstance().getTable("groups").get();
            ArrayList<HashMap<String,String>> newArray=new ArrayList<>();
            for(int i=0;i<array.size();i++){
                if(i!=index){
                    newArray.add(array.get(i));
                }
            }
            newArray.add(data);
            database.getInstance().getTable("groups").clear();
            for(int i=0;i<newArray.size();i++){
                database.getInstance().getTable("groups").insert(newArray.get(i));
            }
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }
    private String unSavePost(HashMap<String,String> data){
        try {
            System.out.println(data.get("currentUser"));
            ArrayList<HashMap<String,String>> array=database.getInstance().getTable(data.get("currentUser")).get();
            System.out.println(2);
            int index=indexOfPstGrpPst(data.get("name"),data.get("groupName"));
            ArrayList<HashMap<String,String>> newArray= new ArrayList<>();
            for(int i=0;i<array.size();i++){
                if(i!=index){
                    newArray.add(array.get(i));
                }
            }
            database.getInstance().getTable(data.get("currentUser")).clear();
            for(int i=0;i<newArray.size();i++){
                database.getInstance().getTable(data.get("currentUser")).insert(newArray.get(i));
            }
            return "massage successfully saved\u0000";
        }catch (Exception e){return e.getMessage()+"\u0000";}
    }
    private String  removePost(HashMap<String,String> data){
        try {
            ArrayList<HashMap<String,String>> feedPosts=database.getInstance().getTable("posts").get();
            ArrayList<HashMap<String,String>> groupPosts=database.getInstance().getTable(data.get("groupName")).get();
            ArrayList<HashMap<String,String>> newFeedPosts=new ArrayList<>();
            ArrayList<HashMap<String,String>> newGroupPosts=new ArrayList<>();
            for(int i=0;i<feedPosts.size();i++){
                if(!feedPosts.get(i).get("title").equals(data.get("title"))){
                    newFeedPosts.add(feedPosts.get(i));
                }
            }
            database.getInstance().getTable("posts").clear();
            for(int i=0;i<newFeedPosts.size();i++){
                database.getInstance().getTable("posts").insert(newFeedPosts.get(i));
            }
            for(int i=0;i<groupPosts.size();i++){
                if(!feedPosts.get(i).get("title").equals(data.get("title"))){
                    newGroupPosts.add(groupPosts.get(i));
                }
            }
            database.getInstance().getTable(data.get("groupName")).clear();
            for(int i=0;i<newGroupPosts.size();i++){
                database.getInstance().getTable(data.get("groupName")).insert(newGroupPosts.get(i));
            }
            return "massage successfully saved\u0000";
        }catch (Exception e){return e.getMessage();}

    }
    private String likeComment(HashMap<String,String> data){
        try {
            ArrayList<HashMap<String,String>> postComments=database.getInstance().getTable(data.get("title")).get();
            ArrayList<HashMap<String,String>> newArray=new ArrayList<>();
            for(int i=0;i< postComments.size();i++){
                if(!postComments.get(i).get("content").equals(data.get("content")) ){
                    newArray.add(postComments.get(i));
                    System.out.println(postComments.get(i));
                }
            }
            newArray.add(data);
            database.getInstance().getTable(data.get("title")).clear();
            for(int i=0;i< newArray.size();i++){
                database.getInstance().getTable(data.get("title")).insert(newArray.get(i));
            }

            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }
    private String likePost(HashMap<String,String> data){
        try {
            int index1=indexOfPstFeed(data.get("title"));
            int index2=indexOfPstGrpPst(data.get("title"),data.get("groupName"));
            ArrayList<HashMap<String,String>> feedPosts=database.getInstance().getTable("posts").get();
            ArrayList<HashMap<String,String>> groupPosts=database.getInstance().getTable(data.get("groupName")).get();
            ArrayList<HashMap<String,String>> newFeedPosts=new ArrayList<>();
            ArrayList<HashMap<String,String>> newGroupPosts=new ArrayList<>();

            for(int i=0;i<feedPosts.size();i++){
                if(index1!=i){
                    newFeedPosts.add(feedPosts.get(i));
                }
            }
            newFeedPosts.add(data);
            database.getInstance().getTable("posts").clear();
            for(int i=0;i<newFeedPosts.size();i++){
                database.getInstance().getTable("posts").insert(newFeedPosts.get(i));
            }

            for(int i=0;i<groupPosts.size();i++){
                if(index2!=i){
                    newGroupPosts.add(groupPosts.get(i));
                }
            }
            newGroupPosts.add(data);
            database.getInstance().getTable(data.get("groupName")).clear();
            for(int i=0;i<newGroupPosts.size();i++){
                database.getInstance().getTable(data.get("groupName")).insert(newGroupPosts.get(i));
            }
            return "massage successfully saved\u0000";
        }catch (Exception e){return "somethings goes wrong\u0000";}
    }

    private int indexOfGrp(String name){
        int index=0;
        ArrayList<HashMap<String,String>> array=database.getInstance().getTable("groups").get();
        for(int i=0;i<array.size();i++){
            if(array.get(i).get("name").equals(name))
                index=i;
        }
        return index;
    }
    private int indexOfPstFeed(String title){
        int index=0;
        ArrayList<HashMap<String,String>> array=database.getInstance().getTable("posts").get();
        for(int i=0;i<array.size();i++){
            if(array.get(i).get("title").equals(title))
                index=i;
        }
        return index;
    }
    private int indexOfPstGrpPst(String title, String groupName){
        int index=0;
        ArrayList<HashMap<String,String>> array=database.getInstance().getTable(groupName).get();
        for(int i=0;i<array.size();i++){
            if(array.get(i).get("title").equals(title))
                index=i;
        }
        return index;
    }

}
