package se.kth.utility;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Exception log handler.
 */
public class ExceptionLogHandler {
    private PrintWriter logStream;

    /**
     * Creates a .txt file for logging exceptions.
     */
    public ExceptionLogHandler() {
        try {
            this.logStream = new PrintWriter(new FileWriter("exceptionLog.txt"), true);
        } catch (IOException ioe) {
            System.out.println("COULD NOT CREATE LOG");
            ioe.printStackTrace();
        }
    }

    /**
     * Logs information about an exception.
     * 
     * @param exceptionToBeLogged the exception to be logged.
     */
    public void logException(Exception exceptionToBeLogged) {
        logStream.println("Exception was thrown at: " + (createTime()));
        exceptionToBeLogged.printStackTrace(logStream);
    }

    /**
     * creates a string containing the time at the point of the calling of the
     * method.
     * 
     * @return
     */
    private String createTime() {
        return LocalDateTime.now().toString();
    }
}
