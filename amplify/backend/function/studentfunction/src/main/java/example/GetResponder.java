package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

public class GetResponder {
    public static String respondTo(APIGatewayProxyRequestEvent request, Context context) throws BadRequestException {
        return "{\"body\": \"Hello GET! Yo path is " + request.getPath() + "\"}";
    }
}
