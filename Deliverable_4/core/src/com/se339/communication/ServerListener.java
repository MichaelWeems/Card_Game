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
    private PixelHockeyGame game;
    private GameScreen screen;

    private Log log;

    public ServerListener(final PixelHockeyGame game){
        port = 8000;
        host = "192.168.1.107";

        this.game = game;
        this.screen = null;

        log = new Log("ServerListener");

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        log.l("reading from server listener");
                        String msg = readFromSocket();
                        log.v(msg, "Server Message");
                        if (msg.equals("startgame")) {
                            callback_startgame();
                        }
                        else if (msg.equals("goal")){
                            callback_GoalScored();
                        }
                        else if(msg.contains("name")){
                            String opp = readFromSocket();
                            //save opp name
                        }
                        else if (msg.contains("velocity")){
                            Scanner scan = new Scanner(readFromSocket());
                            float x = -scan.nextInt();
                            float y = -scan.nextInt();

                            Vector2 v = new Vector2(x,y);
                            callback_VelocityChange(v);
                        }

                    } catch (IOException e){
                        log.e("Error reading from socket: " + e);
                    }
                }
            }
        }).start();
    }

    public void setScreen(GameScreen screen){
        this.screen = screen;
    }

    public void callback_GoalScored(){
        screen.getGameValues().goalScored();
    }

    public void callback_VelocityChange(Vector2 v){
        screen.getGameValues().updateVelocity(v);
    }

    public void callback_startgame() { game.setScreen(new GameScreen(game));}

    public String readFromSocket() throws IOException {
        return game.wb.read();
    }
}
