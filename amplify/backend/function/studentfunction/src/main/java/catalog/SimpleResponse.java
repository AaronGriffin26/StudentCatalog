package catalog;

public class SimpleResponse {
    String body;

    public SimpleResponse(String response) {
        body = response;
    }

    public String getResponse() {
        return body;
    }

    public void setResponse(String newResponse) {
        body = newResponse;
    }
}
