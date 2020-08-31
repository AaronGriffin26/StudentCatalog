package catalog.responders;

import catalog.BadRequestException;
import catalog.responses.SimpleResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

public class DeleteResponder {
    public static SimpleResponse respondTo(APIGatewayProxyRequestEvent request, Context context) throws BadRequestException {
        return new SimpleResponse("Hello DELETE! Yo path is " + request.getPath());
    }
}
