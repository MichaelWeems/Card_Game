package com.se339.Client;

import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.screens.GameScreen;

import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.*;


import java.io.IOException;

/**
 * Created by Zach on 12/5/2016.
 */

public class SearchListener {
    PixelHockeyGame game;
    WebSocket wb;
    boolean session;
    public SearchListener(PixelHockeyGame game){
        this.game = game;
        session = true;
        wb = game.getSocket();
    }

    Thread SearchThread = new Thread() {
        @Override
        public void run() {
            try {
                while (true) {
                    String mess = wb.read();
                    if (mess.equals("startGame")) {
                        game.setScreen(new GameScreen(game));
                    }
                }
            } catch (IOException e) {
                System.out.println("Cannot read response from search listener");
                e.printStackTrace();
            }
        }
    };


}

