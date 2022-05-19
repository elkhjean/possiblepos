package se.kth.utility;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;
/**
 * general log handler
 */
public class ExceptionLogHandler {
    private PrintWriter logStream;

    public ExceptionLogHandler(){
        try {
            this.logStream = new PrintWriter(new FileWriter("exceptionLog.txt"), true);
        } catch (IOException ioe) {
            System.out.println("COULD NOT CREATE LOG");
            ioe.printStackTrace();
        }
    }

    public void logException(Exception exceptionToBeLogged){
        logStream.println("Exception was thrown at: " + (createTime()));
        exceptionToBeLogged.printStackTrace(logStream);
    }

    private String createTime(){
        return LocalDateTime.now().toString();
    }
}
