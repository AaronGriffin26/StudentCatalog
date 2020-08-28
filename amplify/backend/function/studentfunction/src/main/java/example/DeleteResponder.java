package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

public class DeleteResponder {
    public static String respondTo(APIGatewayProxyRequestEvent request, Context context) throws BadRequestException {
        return "{\"body\": \"Hello DELETE! Yo path is " + request.getPath() + "\"}";
    }
}
