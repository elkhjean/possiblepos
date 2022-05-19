package se.kth.integration;

/**
 * Thrown whenever a call to a database fails.
 */
public class DatabaseFailureException extends Exception {
    /**
     * Creates an instance of the exception with a passed message from where
     * exception was thrown.
     * 
     * @param message message from where exception is thrown.
     */
    public DatabaseFailureException(String message) {
        super(message);
    }
}
