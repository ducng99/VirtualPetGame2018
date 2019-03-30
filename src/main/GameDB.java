/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kqn1498
 */
public class GameDB {
    private static final String url = "jdbc:derby:AVPG-DB;create=true";
    
    private static Connection conn;
    
    public static void establishConnection()
    {
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("DB: connected!");
        }
        catch (SQLException e)
        {
            Utilities.log(e);
            System.out.println("Unable to connect to database!");
            System.exit(0);
        }
    }
    
    public static void initializeDB()
    {
        try {
            Statement statement = conn.createStatement();
            
            String createPetTable = "CREATE TABLE Pet ("
                    + "ID int, Name varchar(16), Gender varchar(1), Health int, Hapiness int,"
                    + "isSick boolean, isDirty boolean, bornTime int, lastFeedTime int)";
            
            statement.executeUpdate(createPetTable);
            
            System.out.println("DB: initialized!");
        }
        catch (SQLException e)
        {
            Utilities.log(e);
            System.out.println("Table exists");
        }
    }
    
    public static void createPet(Pet pet)
    {
        try {
            Statement statement = conn.createStatement();
            
            String createPet = "INSERT INTO Pet values("
                    + "1, '"+ pet.getName() +"', '"+ pet.getGender() +"', "+ pet.getHealth() + ", " + pet.getHapiness() + ", "
                    + pet.isSick() + ", " + pet.isDirty() + ", " + pet.getBornTime() + ", " + pet.getLastFeedTime()
                    + ")";
            
            //String createPet = "INSERT INTO Pet values(1, 'Bob1', 'M', 100, 100, 1, false, false, 12413511, 12412551)";
            statement.executeUpdate(createPet);
            
            System.out.println("DB: New Pet created");
        }
        catch (SQLException e)
        {
            Utilities.log(e);
            System.out.println("Pet may existed");
        }
    }
    
    public static void updatePet(Pet pet)
    {
        try {
            Statement statement = conn.createStatement();
            String updatePetInfo = "UPDATE Pet SET "
                    + "Health=" + pet.getHealth() + ", Hapiness=" + pet.getHapiness() + ", isSick=" + pet.isSick() + ", isDirty=" + pet.isDirty() + ", lastFeedTime=" + pet.getLastFeedTime()
                    + " WHERE ID=1";
            statement.executeUpdate(updatePetInfo);
        }
        catch (SQLException e)
        {
            Utilities.log(e);
        }
    }
    
    public static Pet getPet()
    {
        ResultSet rs = null;
        try {
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            
            String readPetInfo = "SELECT * FROM Pet WHERE ID=1";
            
            rs = statement.executeQuery(readPetInfo);
            rs.first();
            
            String name = rs.getString("Name");
            char gender = rs.getString("Gender").charAt(0);
            int health = rs.getInt("Health");
            int hapiness = rs.getInt("Hapiness");
            boolean isSick = rs.getBoolean("isSick");
            boolean isDirty = rs.getBoolean("isDirty");
            int bornTime = rs.getInt("bornTime");
            int lastFeedTime = rs.getInt("lastFeedTime");
            Pet pet = new Pet(name, gender, health, hapiness, isSick, isDirty, bornTime, lastFeedTime);
            
            return pet;
        }
        catch (SQLException e)
        {
            System.out.println("Pet not found or data corrupted");
            return null;
        }
    }
    
    public static void removePet()
    {
        try {
            Statement statement = conn.createStatement();
            String removePet = "DELETE FROM Pet WHERE ID=1";
            statement.executeUpdate(removePet);
        }
        catch (SQLException e)
        {
            Utilities.log(e);
        }
    }
}
