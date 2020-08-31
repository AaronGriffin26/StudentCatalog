

package catalog;

import catalog.responders.*;
import catalog.responses.JsonErrorResponse;
import catalog.responses.SimpleResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.sql.SQLException;
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
                    response.setBody(gson.toJson(GetResponder.respondTo(request, context)));
                    break;
                case "POST":
                    response.setBody(gson.toJson(PostResponder.respondTo(request, context)));
                    break;
                case "PUT":
                    response.setBody(gson.toJson(PutResponder.respondTo(request, context)));
                    break;
                case "PATCH":
                    response.setBody(gson.toJson(PatchResponder.respondTo(request, context)));
                    break;
                case "DELETE":
                    response.setBody(gson.toJson(DeleteResponder.respondTo(request, context)));
                    break;
            }
        } catch (SQLException e) {
            // Handle any errors
            context.getLogger().log("SQLException: " + e.getMessage());
            context.getLogger().log("SQLState: " + e.getSQLState());
            context.getLogger().log("VendorError: " + e.getErrorCode());
            response.setStatusCode(500);
            response.setBody(gson.toJson(new SimpleResponse("ERROR! SQL syntax! " + e.getMessage())));
        } catch (BadRequestException e) {
            response.setStatusCode(400);
            response.setBody(gson.toJson(new SimpleResponse("BAD REQUEST! " + e.getMessage())));
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setBody(gson.toJson(new SimpleResponse("ERROR! " + e.toString())));
        }
        try {
            gson.fromJson(response.getBody(), Object.class);
        } catch (JsonSyntaxException e) {
            response.setStatusCode(500);
            response.setBody(gson.toJson(new JsonErrorResponse("ERROR! Json was not valid! " + e.getMessage(), response.getBody())));
        }
        return response;
    }
}