package com.ayushkv001.server;


import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHandler {
    private static com.ayushkv001.server.DBHandler dbHandler=null;

    private static final String DB_URL = "jdbc:derby:database;create=true";
    public static Connection conn = null;
    public static Statement stmt = null;
    private List<Path> paths = new ArrayList<>();
    private Map<String, List<String>> columnNames = new HashMap<>();
    private static final String ISO_8859_1 = "ISO-8859-1";

    private DBHandler(){
        createConnection();
       createProductTable();
       createOrderTable();
    }

    public static com.ayushkv001.server.DBHandler getInstance(){
        if(dbHandler==null){
            dbHandler= new com.ayushkv001.server.DBHandler();
        }
        return dbHandler;
    }

    void createConnection(){
        try{
            conn = DriverManager.getConnection(DB_URL);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createOrderTable(){
        String query = "CREATE TABLE orders(id integer PRIMARY KEY NOT NULL,renter_name varchar(20),email varchar(30),phone varchar(10),duration varchar(20),cost integer)";
        String TABLE_NAME ="orders";
        try{
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null,null,TABLE_NAME.toUpperCase(),null);
            if(tables.next()){
                System.out.println("Table "+TABLE_NAME+" already exists");
            }
            else {
                stmt.execute(query);
            }
        }catch (SQLException e){
            System.err.println((e.getMessage()+" ----setupOrderTable"));
        }
    }

    private void createProductTable(){
        String query = "CREATE TABLE product(id integer PRIMARY KEY NOT NULL, name varchar (30), type varchar(20), image varchar(30),cost integer,available boolean)";
        String TABLE_NAME ="product";
        try{
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null,null,TABLE_NAME.toUpperCase(),null);
            if(tables.next()){
                System.out.println("Table "+TABLE_NAME+" already exists");
            }
            else {
                stmt.execute(query);
            }
        }catch (SQLException e){
            System.err.println((e.getMessage()+" ----setupProductTable"));
        }
    }

    public ResultSet getResult(String id){
        String query = "SELECT * FROM PRODUCT WHERE type = '"+id+"' AND available = true";
        ResultSet res;
        try{
            stmt= conn.createStatement();
            res=stmt.executeQuery(query);
        }
        catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return res;
    }
    public void putOrder(String id,String renter_name,String email,String phone, String duration,String cost){
        String query = "INSERT INTO ORDERS VALUES ("+id+", '"+renter_name+"','"+email+"','"+phone+"','"+duration+"',"+cost+")";
        try{
            stmt=conn.createStatement();
            stmt.execute(query);
            change(id);
        }
        catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }

    }

    public void putProduct(int id,String name,String type,String image, int cost, boolean available){
        String query = "INSERT INTO PRODUCT VALUES ("+id+", '"+name+"','"+type+"','"+image+"',"+cost+","+available+")";
        System.out.println(query);
        try{
            stmt=conn.createStatement();
            stmt.execute(query);
        }
        catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }

    }
    public void change(String id){
        String query = "UPDATE product SET available = false WHERE id ="+id;
        try{
            stmt=conn.createStatement();
            stmt.execute(query);
        }
        catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }

    }

    public void changeAll(){
        String query = "UPDATE product SET available = true";
        try{
            stmt=conn.createStatement();
            stmt.execute(query);
        }
        catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }

    }

    public void delete(){
        String query = "DELETE FROM orders";
        try{
            stmt=conn.createStatement();
            stmt.execute(query);
        }
        catch (SQLException e){
            System.out.println(e.getLocalizedMessage());
        }

    }
}
