package com.se339.Client;

import java.io.IOException;


import java.io.IOException;

/**
 * Created by Zach on 12/5/2016.
 */

public class SearchListener extends Thread {
    WebSocket wb;
    boolean session;
    public SearchListener(WebSocket wb){
        this.wb = wb;
        session = true;
    }

    public void start(){
        while(session){
            try{
                String str = wb.read();
            }catch(IOException e){
                System.out.println("Cannot recieve message from serverListener");
                e.printStackTrace();
            }
        }
    }

    public void end(){
        session = false;
    }
}

