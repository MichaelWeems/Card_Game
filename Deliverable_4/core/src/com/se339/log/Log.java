package com.se339.log;

/**
 * Created by mweem_000 on 12/1/2016.
 */

public class Log {

    private String obj;

    public Log(String obj){
        this.obj= obj;
    }

    /*
     * Log an error
     */
    public void e(String error){
        String msg = "\n** ERROR *********************************************\n";
        msg +=       "\t" + error;
        printerror(msg);
    }

    private void printerror(String msg){
        printlog(msg);
        print("******************************************************\n");
    }

    /*
     * Log what code is executing
     */
    public void l(String log){
        String msg = "--" + log;
        printlog(msg);
    }

    /*
     * Log if an action is being taken
     */
    public void a(String action){
        String msg = "-Taking Action: " + action;
        printlog(msg);
    }

    private void printlog(String msg){
        msg += "\n\tin class (" + obj + ")";
        print(msg);
    }

    private void print(String msg){
        System.out.println(msg);
    }
}
