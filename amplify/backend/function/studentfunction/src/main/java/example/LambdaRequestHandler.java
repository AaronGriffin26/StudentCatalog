

package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;

public class LambdaRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        var gson = new Gson();
        var response = new APIGatewayProxyResponseEvent();
        var headers = new HashMap<String, String>();
        headers.put("Access-Control-Allow-Origin", "*");
        headers.put("Access-Control-Allow-Headers", "Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token");
        headers.put("Access-Control-Allow-Credentials", "true");
//        headers.put("Access-Control-Allow-Methods", "DELETE,GET,HEAD,OPTIONS,PATCH,POST,PUT");
        headers.put("Access-Control-Allow-Methods", "DELETE,GET,PATCH,POST,PUT");
        headers.put("Access-Control-Expose-Headers", "true");
        response.setStatusCode(200);
        response.setHeaders(headers);
        try {
            switch (request.getHttpMethod()) {
                case "GET":
                    response.setBody(GetResponder.respondTo(request, context));
                    break;
                case "POST":
                    response.setBody(PostResponder.respondTo(request, context));
                    break;
                case "PUT":
                    response.setBody(PutResponder.respondTo(request, context));
                    break;
                case "PATCH":
                    response.setBody(PatchResponder.respondTo(request, context));
                    break;
                case "DELETE":
                    response.setBody(DeleteResponder.respondTo(request, context));
                    break;
            }
        } catch (BadRequestException e) {
            response.setStatusCode(400);
            response.setBody("{\"body\":\"ERROR! " + e.getMessage() + "\"}");
        }
        try {
            gson.fromJson(response.getBody(), Object.class);
        } catch (JsonSyntaxException e) {
            response.setStatusCode(500);
            response.setBody("{\"body\":\"ERROR! Json was not valid! " + e.getMessage() + "\", \"json\":\"" +
                    response.getBody().replace("\"", "\\\"") + "\"}");
        }
        return response;
    }
}