package com.ayushkv001.server;

import java.sql.ResultSet;
import java.sql.SQLException;


// To handle the database 

public class DatabaseManagement {
    public static void main(String args[]) throws SQLException {
        DBHandler dbHandler = DBHandler.getInstance();

        dbHandler.changeAll();
        dbHandler.delete();



//        dbHandler.putProduct(1,"Type1","cycle","Type1c",250,true);
//        dbHandler.putProduct(2,"Type2","cycle","Type2c",200,true);
//        dbHandler.putProduct(3,"Type3","cycle","Type3c",200,false);
//        dbHandler.putProduct(4,"Type1","tennis","Type1t",100,true);
//        dbHandler.putProduct(5,"Type2","tennis","Type2t",110,true);
//        dbHandler.putProduct(6,"Type3","tennis","Type3t",75,true);
//        dbHandler.putProduct(7,"Type1","badminton","Type1b",50,true);
//        dbHandler.putProduct(8,"Type2","badminton","Type2b",20,false);
//        dbHandler.putProduct(9,"Type3","badminton","Type3b",30,false);

    }
}
