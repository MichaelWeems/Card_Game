package com.se339.log;

/**
 * Created by mweem_000 on 12/1/2016.
 */

public class Log {

    private String screen;

    public Log(String screen){
        this.screen = screen;
    }

    public void e(String error){
        String msg = "Error: " + error;
        printlog(msg);
    }

    /*
     * Log code
     */
    public void l(String log){
        String msg = "--" + log;
        printlog(msg);
    }

    public void a(String action){
        String msg = "-Taking Action: " + action;
        printlog(msg);
    }

    private void printlog(String msg){
        msg += "\n\ton screen (" + screen + ")";
        System.out.println(msg);
    }
}
