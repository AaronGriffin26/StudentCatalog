

package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;

public class LambdaRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        var response = new APIGatewayProxyResponseEvent();
        var headers = new HashMap<String, String>();
        headers.put("Access-Control-Allow-Origin", "*");
        headers.put("Access-Control-Allow-Headers", "Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token");
        headers.put("Access-Control-Allow-Credentials", "true");
        headers.put("Access-Control-Allow-Methods", "DELETE,GET,HEAD,OPTIONS,PATCH,POST,PUT");
        headers.put("Access-Control-Expose-Headers", "true");
        response.setStatusCode(200);
        response.setHeaders(headers);
        response.setBody("Hello! Yo path is " + request.getPath());
        return response;
//        if (request.getPath()) {
//            return new APIGatewayProxyResponseEvent() {
//                @Override
//                public Integer getStatusCode() {
//                    return 200;
//                }
//
//                @Override
//                public Map<String, String> getHeaders() {
//                    return super.getHeaders();
//                }
//
//                @Override
//                public String getBody() {
//                    return String.format("Hello %s %s!", request.get("firstName"), request.get("lastName"));
//                }
//            };
//
//        }
//        return new APIGatewayProxyResponseEvent() {
//            @Override
//            public Integer getStatusCode() {
//                return 200;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() {
//                return super.getHeaders();
//            }
//
//            @Override
//            public String getBody() {
//                return "Hello! You must be a GET";
//            }
//        };

    }
}