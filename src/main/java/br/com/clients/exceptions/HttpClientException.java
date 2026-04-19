package br.com.clients.exceptions;

public class HttpClientException extends ClientException {
    private final int statusCode;
    private final String responseBody;

    public HttpClientException(int statusCode, String responseBody) {
        super("Erro HTTP: " + statusCode);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
