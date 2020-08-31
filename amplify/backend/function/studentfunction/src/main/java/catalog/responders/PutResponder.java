package catalog.responders;

import catalog.BadRequestException;
import catalog.SimpleResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

public class PutResponder {
    public static SimpleResponse respondTo(APIGatewayProxyRequestEvent request, Context context) throws BadRequestException {
        return new SimpleResponse("Hello PUT! Yo path is " + request.getPath());
    }
}
