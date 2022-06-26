package database;

import java.util.HashMap;

public class database {
    private static database instance=null;
    public HashMap<String,table> tables;
    private database(){
        tables=new HashMap<>();
    }

    public static database getInstance(){
        if(instance==null){
            instance=new database();
        }
        return instance;
    }
    public void addTable(String name , table table){ tables.put(name,table);}
    public table getTable(String name){
        return tables.get(name);
    }
}
