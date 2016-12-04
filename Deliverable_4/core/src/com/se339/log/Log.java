package com.se339.log;

/**
 * Created by mweem_000 on 12/1/2016.
 */

public class Log<E> {

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



    public void v(E x, String xName){
        String o = "" + x;
        printv(o, xName);
    }

    public void o(Object obj, String oName){
        String o = obj.toString();
        printv(o, oName);
    }
/*
    public void v(int x, String xName){
        String o = "" + x;
        printv(o, xName);
    }

    public void v(Object obj, String oName){
        String o = obj.toString();
        printv(o, oName);
    }
*/
    private void printv(String o, String oName){
        String msg = "-- Variable (" + oName + "):\n\t\t" + o;
        print(msg);
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
