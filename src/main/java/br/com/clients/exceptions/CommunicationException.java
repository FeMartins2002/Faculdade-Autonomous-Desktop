package br.com.clients.exceptions;

public class CommunicationException extends ClientException {

    public CommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
