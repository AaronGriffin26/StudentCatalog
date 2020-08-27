/* Amplify Params - DO NOT EDIT
	AUTH_STUDENTCATALOG1FC11454_USERPOOLID
	STORAGE_IMAGESTORAGE_BUCKETNAME
Amplify Params - DO NOT EDIT */

package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaRequestHandler implements RequestHandler<RequestClass, ResponseClass> {

    public ResponseClass handleRequest(RequestClass request, Context context) {
        String greetingString = String.format("Hellole %s %s!", request.firstName, request.lastName);
        return new ResponseClass(greetingString);
    }
}