package com.se339.Client;

import com.badlogic.gdx.math.Vector2;
import com.se339.log.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Zach on 12/5/2016.
 */

public class webSocket {
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Log l;

    public webSocket(){
        l = new Log("WebSocket class");
        try {
            socketConnect();
        } catch (UnknownHostException e) {
            l.e("Unknown Host, Cannot Connect");
            e.printStackTrace();
        } catch (IOException e) {
            l.e("Cannot Connect to Server");
            e.printStackTrace();
        }
    }

    //Creates Connection to Web Socket
    public void socketConnect() throws UnknownHostException, IOException {
        String ip = "10.65.222.239";
//        String ip = "localhost";
        int port = 8000;
        System.out.println("[Connecting to socket...]");
        this.socket = new Socket(ip, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    public void joinGame(){
        //Print 'Searching for player'
        out.println("joinGame&");
        try{
            String mess = in.readLine();
        }catch(IOException e){
            l.e("Cannot join game");
        }
    }

    public void endSearch(){
        out.println("endSearch&");
    }

    public void sendMove(Vector2 velocity){
        out.println(velocity+"&");
    }
}
