package br.com.exceptions;

public class CommunicationException extends RuntimeException {

    public CommunicationException(String message) {
        super(message);
    }
}
