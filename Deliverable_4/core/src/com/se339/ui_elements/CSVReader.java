package com.se339.ui_elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.badlogic.gdx.graphics.g2d.ParticleEmitter.SpawnShape.line;

/**
 * Created by Zach on 12/3/2016.
 */

public class CSVReader {

    private String fileName;
    private ArrayList<String[]> friends;
    private static String line = "";
    private static String split = ",";

    public CSVReader(String name){
        fileName = name;
        friends = new ArrayList();
        try  {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] user = line.split(split);
                friends.add(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> getFriends(){
        return friends;
    }

    public void editInfo(String name, double win){

        double lose = 1 - win;
        friends = new ArrayList();
        boolean newPlayer = true;
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while((line = br.readLine()) != null){
                String[] temp = line.split(split);
                if(temp[0].equals(name)){
                    win = Double.parseDouble(temp[1]) + win;
                    lose = Double.parseDouble(temp[2]) + lose;
                    temp = getStats(name, win, lose);
                    newPlayer = false;
                }
                friends.add(temp);
            }
            if(newPlayer){
                String[] temp = getStats(name, win, lose);
                friends.add(temp);
            }
            fileWrite();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public String[] getStats(String name, double win, double lose){
        DecimalFormat df = new DecimalFormat("0.##");
        String[] temp = new String[4];
        temp[0] = name;
        temp[1] = Double.toString(win);
        temp[2] = Double.toString(lose);
        lose = Math.max(lose, 1);
        double ratio = win/lose;
        temp[3] = df.format(ratio);
        return temp;
    }

    public void fileWrite(){
        try {
            FileWriter fw = new FileWriter(fileName);
            for(String[] s:friends){
                String temp = s[0]+","+s[1]+","+s[2]+","+s[3]+"\n";
                fw.append(temp);
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
