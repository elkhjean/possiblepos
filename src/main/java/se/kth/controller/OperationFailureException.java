package se.kth.controller;
/**
 * Thrown when an operation has failed.
 */
public class OperationFailureException extends Exception {

    public OperationFailureException(String message, Exception cause){
        super(message, cause);
    }
}
