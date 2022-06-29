package database;

import utils.convertor;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class table {
    private String path;
    public table(String path){
        this.path=path;
    }

    public void insert(HashMap<String,String> row)throws Exception{
        FileWriter fr=new FileWriter(path,true);
        fr.write(convertor.mapToString(row)+"\n");
        fr.close();
    }

    public void remove(){
        File file = new File(path);
        file.delete();
    }

    public void remane(String newPath){
        File file = new File(path);
        file.renameTo(new File(newPath));
    }

    public void clear()throws Exception{
        File file = new File(path);
        PrintWriter writer = new PrintWriter(file);
        writer. print("");
        writer. close();
    }

    public ArrayList<HashMap<String,String>> get() {
        try {
            File file = new File(path);
            Scanner input = new Scanner(file);
            ArrayList<HashMap<String, String>> data = new ArrayList<>();

            while (input.hasNextLine()) {
                String str = input.nextLine();
                data.add(convertor.stringToMap(str));
            }
            return data;
        } catch (Exception e) {System.out.println(e.getMessage());}
        return new ArrayList<>();
    }
}
