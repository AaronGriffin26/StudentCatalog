package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.google.gson.Gson;

public class PostResponder {
    public static String respondTo(APIGatewayProxyRequestEvent request, Context context) throws BadRequestException {
        if (request.getPath().equals("/student") || request.getPath().equals("/student/"))
            return greet(request, context);
        return "{\"body\": \"Hello " + request.getBody().replace("\"", "\\\"") + "! Yo path is " + request.getPath() + "\"}";
    }

    private static String greet(APIGatewayProxyRequestEvent request, Context context) {
        var gson = new Gson();
        FirstNameLastName person = gson.fromJson(request.getBody(), FirstNameLastName.class);
        return "{\"body\": \"Hello " + person.firstName + " " + person.lastName + "! Yo path is " + request.getPath() + "\"}";
    }
}
