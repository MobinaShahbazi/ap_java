package database;

import utils.convertor;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
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
        } catch (Exception e) {}
        return new ArrayList<>();
    }
}
