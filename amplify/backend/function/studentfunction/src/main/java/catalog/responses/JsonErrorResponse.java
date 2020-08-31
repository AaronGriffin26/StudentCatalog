package catalog.responses;

public class JsonErrorResponse {
    String body;
    String json;

    public JsonErrorResponse(String response, String json) {
        body = response;
        this.json = json;
    }

    public String getResponse() {
        return body;
    }

    public void setResponse(String newResponse) {
        body = newResponse;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String newJson) {
        json = newJson;
    }
}
