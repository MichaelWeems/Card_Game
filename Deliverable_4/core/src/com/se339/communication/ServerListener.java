package com.se339.communication;

import com.badlogic.gdx.math.Vector2;
import com.se339.log.Log;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.screens.GameScreen;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by mweem_000 on 12/5/2016.
 */

public class ServerListener {

    private int port;
    private String host;

    private Log log;

    public ServerListener(final PixelHockeyGame game, int p, String h){
        port = p;
        host = h;

        log = new Log("ServerListener");

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        String msg = game.getSocket().read();
                        if (msg.equals("startgame")) {
                            game.setScreen(new GameScreen(game));
                        }
                        else if (msg.equals("goal")){
                            //add point to player
                        }
                        else if(msg.contains("name")){
                            String opp = game.getSocket().read();
                            //save opp name
                        }
                        else if (msg.contains("velocity")){
                            Scanner scan = new Scanner(game.getSocket().read());
                            float x = scan.nextInt();
                            float y = scan.nextInt();
                            Vector2 v = new Vector2(x,y);

                        }

                    } catch (IOException e){
                            log.e("Error reading from socket: " + e);
                    }
                }
            }
        }).start();
    }
}
