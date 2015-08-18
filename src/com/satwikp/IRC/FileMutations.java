package com.satwikp.IRC;

/**
 * Created by panis on 8/17/15.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class FileMutations {
    public void FileMutations(){
        try{
            File folder = new File("IRC-Bot");
            folder.mkdir();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void createFolder(String fName){
        try{
            File folder = new File(fName);
            if(folder.mkdir()){
                System.out.println(fName + "created.");
            }
            else{
                System.out.println("Folder already exists.");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
     public void createFile()
    }
}
