package catalog.responses;

import catalog.entities.FirstNameLastName;

public class NamesResponse {
    FirstNameLastName[] names;

    public NamesResponse(FirstNameLastName[] response) {
        names = response;
    }

    public FirstNameLastName[] getResponse() {
        return names;
    }

    public void setResponse(FirstNameLastName[] newResponse) {
        names = newResponse;
    }
}
